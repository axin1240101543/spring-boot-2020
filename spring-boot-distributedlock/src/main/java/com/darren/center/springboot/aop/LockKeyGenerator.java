package com.darren.center.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * key生成器
 */
public interface LockKeyGenerator {

    String getLockKey(ProceedingJoinPoint point);

}
