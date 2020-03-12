package com.darren.center.springboot.utils;

import java.util.concurrent.Semaphore;


public class SemaphoreWorker implements Runnable{

    private String name;

    private Semaphore semaphore;

    public SemaphoreWorker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            log("is wait for a permit");
            semaphore.acquire();
            log("acquire a permit");
            log("executed");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            log("release a permit");
            semaphore.release();
        }
    }

    private void log(String msg){
        if (name == null){
            name = Thread.currentThread().getName();
        }
        System.out.println(name + "    " + msg);
    }
}
