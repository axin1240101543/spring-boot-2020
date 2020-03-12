package com.darren.center.springboot.utils;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS
 */
public class AtomicReferenceTest {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);


    public static void main(String[] args){
        count.compareAndSet(0, 2);//2
        count.compareAndSet(0, 3);
        count.compareAndSet(1, 1);
        count.compareAndSet(2, 4);//4
        count.compareAndSet(3, 5);
        System.out.println(count);
    }

}
