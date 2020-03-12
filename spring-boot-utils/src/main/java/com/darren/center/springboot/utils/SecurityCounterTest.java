package com.darren.center.springboot.utils;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * 线程安全计数器
 */
public class SecurityCounterTest {

    private final AtomicLong counter = new AtomicLong();
    private volatile long counter1;
    private static final AtomicLongFieldUpdater<SecurityCounterTest> updater =
            AtomicLongFieldUpdater.newUpdater(SecurityCounterTest.class, "counter1");

    /**
     * 包装类实现
     */
    public void increase(){
        long c = counter.incrementAndGet();
        System.out.println(c);
    }

    /**
     * 原始数据类型实现
     */
    public void increase1(){
        long c = updater.incrementAndGet(this);
        System.out.println(c);
    }

    public static void main(String[] args) {
        SecurityCounterTest c = new SecurityCounterTest();
        c.increase();
        c.increase1();
    }
}
