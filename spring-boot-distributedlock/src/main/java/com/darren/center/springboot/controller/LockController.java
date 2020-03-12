package com.darren.center.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.darren.center.springboot.annotation.CacheLock;
import com.darren.center.springboot.annotation.CacheParam;
import com.darren.center.springboot.component.RedisLockService;
import com.darren.center.springboot.entity.User;
import com.darren.center.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 测试Redis分布式锁
 */
@Slf4j
@RestController
public class LockController extends BaseController{

    private static final String LOCKNAME = "test";

    @Autowired
    private UserService userService;

    @Autowired
    private RedisLockService redisLockService;

    @CacheLock(prefix = "lock")
    @GetMapping("/lock")
    public String query(@CacheParam(name = "token") @RequestParam String token){
        return "success" + token;
    }

    @GetMapping("/redisLock")
    @ResponseBody
    public String redisLock(){
        String lockValue = redisLockService.tryLock(LOCKNAME, (long)10000, (long)10000);
        if (StringUtils.isBlank(lockValue)){
            log.warn("未获取到锁, lockName:{}, lockValue:{}", LOCKNAME, lockValue);
            return "未获取到锁";
        }
        List<User> list = userService.selectUserList(new HashMap<>());
        if (!redisLockService.releaseLock(LOCKNAME, lockValue)){
            log.error("释放锁失败, lockName:{}, lockValue:{}", LOCKNAME, lockValue);
        }
        return JSONObject.toJSONString(list);
    }

}
