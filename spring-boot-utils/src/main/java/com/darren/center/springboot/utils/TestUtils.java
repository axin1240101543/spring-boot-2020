package com.darren.center.springboot.utils;

import java.util.concurrent.Executors;

public class TestUtils {

    public static void main(String[] args) throws Exception{
        Runnable runnable = () -> {
            System.out.println("hello world");
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        System.out.println("end");

        Executors.newFixedThreadPool(1).submit(runnable).get();

        System.out.println("hello world");
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        int nowThreads = topGroup.activeCount();
        Thread[] lstThreads = new Thread[nowThreads];
        topGroup.enumerate(lstThreads);
        for (int i = 0; i < nowThreads; i++) {
            System.out.println("线程number：" + i + " = " + lstThreads[i].getName());
        }

    }

}
