package com.darren.center.springboot.utils;

import java.util.concurrent.Semaphore;

public class MySemaphoreWorker implements Runnable{

    private Semaphore semaphore;

    public MySemaphoreWorker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("executed");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
