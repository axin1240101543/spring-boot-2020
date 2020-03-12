package com.darren.center.springboot.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 锁的注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheLock {

    /**
     * key的前缀，默认空
     * @return
     */
    String prefix() default "";

    /**
     * 过期时间，默认5秒
     * @return
     */
    int expire() default 5;

    /**
     * 过期时间单位，默认秒
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * key的分隔符，默认：
     * @return
     */
    String separator() default ":";

}
