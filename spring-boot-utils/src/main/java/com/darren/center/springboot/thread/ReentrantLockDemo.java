package com.darren.center.springboot.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock公平性测试
 */
public class ReentrantLockDemo implements Runnable{

    ReentrantLock lock = new ReentrantLock(false);

    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock");
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        Thread t1 = new Thread(reentrantLockDemo);
        Thread t2 = new Thread(reentrantLockDemo);
        t1.start();
        t2.start();
    }
}
