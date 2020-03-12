package com.darren.center.springboot.aop;

import com.alibaba.fastjson.JSONObject;
import com.darren.center.springboot.context.Context;
import com.darren.center.springboot.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class SystemLogger {

    @Value("${isLogger}")
    private boolean isLogger;

    private static long startTime = 0;


    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request = this.getRequest();
        Context context = BaseController.getContext(request);
        log.info("seq:{}, request body:{}", context.getMsgSeq(), Arrays.toString(joinPoint.getArgs()));
        if (isLogger){
            startTime = System.currentTimeMillis();
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            Method method = signature.getMethod();
            log.info("请求路径 : " + request.getRequestURL().toString());
            log.info("请求方法 : " + request.getMethod());
            log.info("访问IP : " + request.getRemoteAddr());
            log.info("访问方法 : " + signature.getDeclaringTypeName() + "." + method.getName());
            log.info("请求方法参数：" + Arrays.toString(joinPoint.getArgs()));
        }
    }

    @AfterReturning(returning = "ret", pointcut = "pointcut()")
    public void doAfterReturning(Object ret){
        HttpServletRequest request = this.getRequest();
        Context context = BaseController.getContext(request);
        log.info("seq:{}, response body:{}", context.getMsgSeq(), JSONObject.toJSONString(ret));
        if (isLogger){
            log.info("响应数据 : " + JSONObject.toJSONString(ret));
            log.info("响应时间 : " + (System.currentTimeMillis() - startTime) + " ms");
        }
    }

    private HttpServletRequest getRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

}
