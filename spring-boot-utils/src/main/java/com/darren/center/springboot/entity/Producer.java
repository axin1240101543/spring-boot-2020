package com.darren.center.springboot.entity;

import java.util.concurrent.BlockingQueue;

public class Producer  implements Runnable{

    private final BlockingQueue blockingQueue;

    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            try {
                blockingQueue.put(i);
                System.out.println("Producer:" + i);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException" + e);
            }
        }
    }

}
