package com.darren.center.springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 构造分页参数
 */
@Slf4j
@Aspect
@Component
public class Paging {

    private final String PAGE_FLAG = "^*listPage*";

    /**
     * 对controller包下有@RequestMapping注解的所有方法
     * 1、* *..controller..*(..)
     * 2、public * *(..)
     * 3、* com.darren.center.springboot.controller.UserController.*(..)
     * 4、* com.darren.center.springboot.controller..*.*(..)   第一个*：返回类型；第二个..：当前包 + 子包；第三个*：类；第四个*：方法；第五个..：参数
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("execution(* *..controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object process(ProceedingJoinPoint point) throws Throwable{
        Object[] args = point.getArgs();
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        if (methodName.matches(PAGE_FLAG)){
            double page = Double.valueOf(request.getParameter("page"));
            double limit = Double.valueOf(request.getParameter("limit"));
            int offset = (int)((page - 1) * limit);
            for (Object arg:args){
                if (arg instanceof Map<?, ?>){
                    ((Map<String, Integer>)arg).put("offset", offset);
                    ((Map<String, Integer>)arg).put("limit", Integer.valueOf(request.getParameter("limit")));
                }
            }
        }
        return point.proceed(args);
    }

}
