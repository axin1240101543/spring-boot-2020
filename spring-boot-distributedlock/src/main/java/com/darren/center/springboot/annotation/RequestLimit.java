package com.darren.center.springboot.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 限流
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)//最先优先执行
public @interface RequestLimit {

    /**
     * 时间段内允许访问的次数
     * @return
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 时间段，默认60秒
     * @return
     */
    long time() default 60000;

}
