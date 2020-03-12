package com.darren.center.springboot.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Redis分布式锁实现
 * 采用SETNX + EXPIRE命令
 */
@Slf4j
@Component
public class RedisLockService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String PREFIX = "lock";
    private static final String SEPARATOR = ":";

    /**
     * 获取锁
     * @param lockName 锁名称
     * @param acquireTimeout 获取锁的超时时间
     * @param timeout 自动释放锁的超时时间
     * @return lockValue 锁的值
     */
    public String tryLock(String lockName, long acquireTimeout, long timeout){
        String lockKey = new StringBuilder(PREFIX).append(SEPARATOR).append(lockName).toString();
        String lockValue = UUID.randomUUID().toString();
        log.info("获取锁开始, lockKey:{}, lockValue:{}", lockName, lockValue);
        RedisConnectionFactory redisConnectionFactory = null;
        RedisConnection redisConnection = null;
        try {
            redisConnectionFactory = stringRedisTemplate.getConnectionFactory();
            redisConnection = redisConnectionFactory.getConnection();
            while (System.currentTimeMillis() < (System.currentTimeMillis() + acquireTimeout)){
                if (redisConnection.setNX(lockKey.getBytes(), lockValue.getBytes())){
                    redisConnection.expire(lockKey.getBytes(), (int)timeout/1000);
                    RedisConnectionUtils.releaseConnection(redisConnection, redisConnectionFactory);
                    return lockValue;
                }
            }
        }catch (Exception e){
            if (-1 == redisConnection.ttl(lockKey.getBytes())){
                redisConnection.expire(lockKey.getBytes(), (int)timeout/1000);
            }
            log.error("获取锁失败:{}", e.getMessage(), e);
        }finally {
            if (null != redisConnectionFactory && null != redisConnection){
                RedisConnectionUtils.releaseConnection(redisConnection, redisConnectionFactory);
            }
            log.info("获取锁结束, lockKey:{}, lockValue:{}", lockName, lockValue);
        }
        return null;
    }

    /**
     * 释放锁
     * @param lockName 锁名称
     * @param lockValue 锁值
     * @return
     */
    public boolean releaseLock(String lockName, String lockValue){
        if (StringUtils.isEmpty(lockValue)){
            return false;
        }
        String lockKey = new StringBuilder(PREFIX).append(SEPARATOR).append(lockName).toString();
        RedisConnectionFactory redisConnectionFactory = null;
        RedisConnection redisConnection = null;
        try {
            redisConnectionFactory = stringRedisTemplate.getConnectionFactory();
            redisConnection = redisConnectionFactory.getConnection();
            while (true){
                //监视一个（或多个）key，如果事务执行之前这个（或这些）key被命令锁改动，那么事务将被打断。
                redisConnection.watch(lockKey.getBytes());
                byte[] redisLockValue = redisConnection.get(lockKey.getBytes());
                if (null == redisLockValue){
                    //取消WATCH命令对所有key的监视
                    //如果EXEC（执行事务后，WATCH效果产生）或DISCARD（取消事务同时也会取消所有key的监视）命令先被执行，则UNWATCH命令不再执行。
                    redisConnection.unwatch();
                    return false;
                }
                log.info("redisLockValue:{}", new String(redisLockValue));
                if (lockValue.equals(new String(redisLockValue))){
                    //标记一个事务块的开始。
                    //事务块内的多条命令会按照先后顺序被放进一个队列当中，最后由EXEC命令原子性（atomic）执行。
                    redisConnection.multi();
                    redisConnection.del(redisLockValue);
                    //执行命令块内的命令。
                    //如果事务块内有某个（或某些）key正在被WATCH命令监视，那么EXEC命令只有在这个（或这些）key没有被其他命令所改动的情况下并生效，否则该事务被打断（abort）。
                    List<Object> results =  redisConnection.exec();
                    if (CollectionUtils.isEmpty(results)){
                        continue;
                    }
                    return true;
                }
                redisConnection.unwatch();
                return false;
            }
        }finally {
            if (null != redisConnectionFactory && null != redisConnection){
                RedisConnectionUtils.releaseConnection(redisConnection, redisConnectionFactory);
            }
        }
    }

}
