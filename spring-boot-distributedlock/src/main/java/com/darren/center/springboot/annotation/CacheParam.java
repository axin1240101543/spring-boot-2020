package com.darren.center.springboot.annotation;

import java.lang.annotation.*;

/**
 * 锁参数的注解
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {

    /**
     * 字段名称
     * @return
     */
    String name() default "";

}
