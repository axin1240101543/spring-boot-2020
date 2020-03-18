package com.darren.center.springboot.thread;

/**
 * 使用notify、notifyAll唤醒waiting状态的锁
 */
public class NotifyAndNotifyAllDemo {

    public static void main(String[] args) {
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread a is waiting to get lock");
                synchronized (lock){
                    try {
                        System.out.println("thread a get lock");
                        Thread.sleep(20);
                        System.out.println("thread a do wait method");
                        lock.wait();
                        System.out.println("thread a is done");
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
                System.out.println("thread b is waiting to get lock");
                synchronized (lock){
                    try {
                        System.out.println("thread b get lock");
                        System.out.println("thread b is sleeping 10 ms");
                        Thread.sleep(20);
                        System.out.println("thread b is done");
                        lock.notify();
                        //lock.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
