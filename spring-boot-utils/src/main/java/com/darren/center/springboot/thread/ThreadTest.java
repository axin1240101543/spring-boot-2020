package com.darren.center.springboot.thread;

public class ThreadTest {

    private static void attack(){
        System.out.println("Fight");
        System.out.println("current thread is :" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                attack();
            }
        };
        //直接调用run方法
        //t.run();
        //调用start方法
        t.start();
    }

}
