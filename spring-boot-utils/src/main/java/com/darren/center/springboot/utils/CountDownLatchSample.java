package com.darren.center.springboot.utils;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchSample {

    public static void main(String[] args) throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 0; i < 5; i++){
            Thread thread = new Thread(new FirstBatchWorker(latch));
            thread.start();
        }

        for (int i = 0; i < 5; i++){
            Thread thread = new Thread(new SecondBatchWorker(latch));
            thread.start();
        }
        // 注意这里也是演示目的的逻辑，并不是推荐的协调方式
        while (latch.getCount() != 1){
            Thread.sleep(100L);
        }
        System.out.println("Wait for first batch finish");
        latch.countDown();
    }

}
