package com.darren.center.springboot.utils;

import java.util.concurrent.CountDownLatch;

public class SecondBatchWorker implements Runnable{

    private CountDownLatch countDownLatch;

    public SecondBatchWorker(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.println("Second batch executed");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
