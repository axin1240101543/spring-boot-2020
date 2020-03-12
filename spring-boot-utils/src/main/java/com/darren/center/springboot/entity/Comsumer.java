package com.darren.center.springboot.entity;

import java.util.concurrent.BlockingQueue;

public class Comsumer implements Runnable{

    private final BlockingQueue blockingQueue;

    public Comsumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Object obj = blockingQueue.take();
                System.out.println("Comsumer:" + obj);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException" + e);
            }
        }
    }

}
