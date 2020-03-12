package com.darren.center.springboot.utils;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 用于更新某个类的实例的某个 field 的值，被更新的 field 必须用 volatile 修饰，并且不能用 static 修饰。
 */
public class AtomicIntegerFieldUpdaterTest {

    /**
     * 指定要更新哪个类（的实例），以及哪个 field
     */
    private static final AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterTest> updater
            = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class, "count");

    /**
     * 被更新的 field 必须是 volatile 的字段，并且不能用 static 修饰
     */
    volatile int count = 100;

    public static void main(String[] args){
        AtomicIntegerFieldUpdaterTest atomic = new AtomicIntegerFieldUpdaterTest();
        System.out.println("a:" + updater.compareAndSet(atomic, 100, 200));
        System.out.println("b:" + updater.compareAndSet(atomic, 200, 300));
        System.out.println(updater.get(atomic));
    }

}
