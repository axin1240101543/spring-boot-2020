package com.darren.center.springboot.utils;

import com.darren.center.springboot.enums.ReadWriteLockEnum;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

/**
 * 使用读写锁
 */
public class ReadWriterLockDemo {

    private static int count = 0;

    public static int getCount(){
        return count;
    }

    public static void addCount(){
        ++count;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = new ThreadPoolExecutor(5, 1000, 60L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //使用读写锁
                    Lock lock = ReadWriteLockEnum.INSTANCE.writeLock();
                    lock.lock();
                    ReadWriterLockDemo.addCount();
                    countDownLatch.countDown();
                    lock.unlock();
                }
            });
        }
        countDownLatch.await();
        executor.shutdown();
        System.out.println(getCount());
    }

}
