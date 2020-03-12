package com.darren.center.springboot.id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;


public class CacheIDGenerator implements IDGenerator {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ID next(String key) {
        long id;
        try {
            id = stringRedisTemplate.opsForValue().increment(key, 1);
        } catch (Exception e) {
            throw new RuntimeException("when generate id for " + key, e);
        }
        if (id == -1) {
            throw new RuntimeException("the generate id cache has not be init");
        }
        return new ID(id, System.currentTimeMillis() / 1000);
    }

    @Override
    public ID next(String key, String idFormat) {
        long id;
        try {
            id = stringRedisTemplate.opsForValue().increment(key, -1);
            id = Long.valueOf(String.format(idFormat, id));
        } catch (Exception e) {
            throw new RuntimeException("when generate id for " + key, e);
        }
        if (id == -1) {
            throw new RuntimeException("the generate id cache has not be init");
        }
        return new ID(id, System.currentTimeMillis() / 1000);
    }

    @Override
    public ID next(String key, IDFormat idFormat) {
        long id;
        try {
            id = stringRedisTemplate.opsForValue().increment(key, -1);
            id = idFormat.format(id);
        } catch (Exception e) {
            throw new RuntimeException("when generate id for " + key, e);
        }
        if (id == -1) {
            throw new RuntimeException("the generate id cache has not be init");
        }
        return new ID(id, System.currentTimeMillis() / 1000);
    }

    @Override
    public ID next(String key, IDFormat idFormat, int timeout) {
        long id;
        try {
            if (stringRedisTemplate.opsForValue().get(key) == null) {
                id = stringRedisTemplate.opsForValue().increment(key, timeout);
            } else {
                id = stringRedisTemplate.opsForValue().increment(key, -1);
            }
            id = idFormat.format(id);
        } catch (Exception e) {
            throw new RuntimeException("when generate id for " + key, e);
        }
        if (id == -1) {
            throw new RuntimeException("the generate id cache has not be init");
        }
        return new ID(id, System.currentTimeMillis() / 1000);
    }

    @Override
    public List<Object> getInstanceKeys() {
        return null;
    }
}
