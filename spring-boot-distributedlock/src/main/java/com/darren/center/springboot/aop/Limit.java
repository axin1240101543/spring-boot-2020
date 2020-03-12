package com.darren.center.springboot.aop;

import com.darren.center.springboot.annotation.RequestLimit;
import com.darren.center.springboot.exception.RequestLimitException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 限流
 */
@Slf4j
@Aspect
@Component
public class Limit {

    private Map<String, Integer> map = new HashMap<>();


    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(limit)")
    public void requestLimit(final JoinPoint point, RequestLimit limit) throws RequestLimitException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getLocalAddr();
        String url = request.getRequestURL().toString();
        final String key = "request_limit_".concat(url).concat(ip);
        if (map.get(key) == null || map.get(key) == 0) {
            map.put(key, 1);
        } else {
            map.put(key, map.get(key) + 1);
        }
        int count = map.get(key);
        if (count > 0) {
            //创建一个定时器
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    map.remove(key);
                }
            };
            //不断的清除key
            timer.schedule(task, limit.time());
        }
        if (count > limit.count()){
            log.info("当前请求次数[" + count +"]用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
            throw new RequestLimitException();
        }
    }

}
