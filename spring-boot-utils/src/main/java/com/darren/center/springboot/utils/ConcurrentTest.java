package com.darren.center.springboot.utils;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发测试
 */
public class ConcurrentTest {

    private static int threadCount = 200;
    private static int clientTotal = 5000;
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        add();
                        semaphore.release();
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(count.get());
    }

    private static void add(){
        count.incrementAndGet();
    }

}
