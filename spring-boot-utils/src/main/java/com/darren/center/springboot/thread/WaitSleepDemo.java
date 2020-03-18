package com.darren.center.springboot.thread;

public class WaitSleepDemo {

    public static void main(String[] args) {
        //testWait();
        testSleep();
    }

    /**
     * 验证wait让出CPU且释放锁
     */
    public static void testWait(){
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread a is waiting to get lock");//1
                synchronized (lock){
                    try {
                        System.out.println("thread a get lock");//2
                        Thread.sleep(20);
                        System.out.println("thread a do wait method");//4  执行wait方法，让出CPU并释放锁1s
                        lock.wait(1000);
                        System.out.println("thread a is done");//7
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //暂停了10ms，让线程a先执行
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread b is waiting to get lock");//3  此时需要获取lock的同步锁
                synchronized (lock){
                    try {
                        System.out.println("thread b get lock");//6  线程a执行wait方法，让出CPU并释放锁1s，线程b得到锁，并开始执行
                        System.out.println("thread b is sleeping 10 ms");//6
                        Thread.sleep(20);
                        System.out.println("thread b is done");//6
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 验证sleep让出CPU但改变锁行为
     */
    public static void testSleep(){
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread a is waiting to get lock");//1
                synchronized (lock){
                    try {
                        System.out.println("thread a get lock");//2
                        Thread.sleep(20);
                        System.out.println("thread a do wait method");//4  执行sleep方法，让出CPU但不释放锁
                        Thread.sleep(1000);
                        System.out.println("thread a is done");//5 继续执行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //暂停了10ms，让线程a先执行
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread b is waiting to get lock");//3  此时需要获取lock的同步锁
                synchronized (lock){
                    try {
                        System.out.println("thread b get lock");//6  线程a执行sleep方法，让出CPU但不释放锁，等待线程a执行完毕，然后获取锁
                        System.out.println("thread b is sleeping 10 ms");//6
                        lock.wait(20);
                        System.out.println("thread b is done");//6
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}