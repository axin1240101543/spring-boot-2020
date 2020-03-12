package com.darren.center.springboot.aop;

import com.darren.center.springboot.annotation.CacheLock;
import com.darren.center.springboot.annotation.CacheParam;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * key生成器实现类
 */
public class LockKeyGeneratorImpl implements LockKeyGenerator {

    @Override
    public String getLockKey(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        //获取注解
        CacheLock lockAnnotation = method.getAnnotation(CacheLock.class);
        final Object[] args = point.getArgs();
        final Parameter[] params = method.getParameters();
        StringBuilder builder = new StringBuilder();
        //默认解析方法里面带 CacheParam 注解的属性,如果没有尝试着解析实体对象中的
        for (int i = 0; i < params.length; i++){
            final CacheParam paramAnnotation = params[i].getAnnotation(CacheParam.class);
            if (null == paramAnnotation){
                continue;
            }
            builder.append(lockAnnotation.separator()).append(args[i]);
        }
        if (StringUtils.isEmpty(builder.toString())){
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++){
                final Object arg = args[i];
                final Field[] fields = arg.getClass().getDeclaredFields();
                for (Field field:fields){
                    final CacheParam paramAnnotation = field.getAnnotation(CacheParam.class);
                    if (null == paramAnnotation){
                        continue;
                    }
                    field.setAccessible(true);
                    builder.append(lockAnnotation.separator()).append(ReflectionUtils.getField(field, arg));
                }
            }
        }
        return lockAnnotation.prefix() + builder.toString();
    }
}
