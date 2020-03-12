package com.darren.center.springboot.utils;

import java.util.concurrent.CountDownLatch;

public class FirstBatchWorker implements Runnable{

    private CountDownLatch countDownLatch;

    public FirstBatchWorker(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("First batch executed");
        countDownLatch.countDown();
    }
}
