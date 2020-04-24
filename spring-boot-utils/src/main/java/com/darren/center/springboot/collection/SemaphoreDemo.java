package com.darren.center.springboot.collection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore的使用
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        //只能同时五个线程访问
        final Semaphore semaphore = new Semaphore(5);
        //模拟20个客户端访问
        for (int index = 0; index < 20; index++) {
            final int no = index;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        //获取许可
                        semaphore.acquire();
                        System.out.println("Accessing:" + no);
                        Thread.sleep((long) (Math.random()*10000));
                        //释放许可
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executor.execute(runnable);
        }
        //退出线程池
        executor.shutdown();
    }

}
