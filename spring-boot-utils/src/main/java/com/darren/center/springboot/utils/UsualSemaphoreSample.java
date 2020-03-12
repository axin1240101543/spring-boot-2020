package com.darren.center.springboot.utils;

import java.util.concurrent.Semaphore;

public class UsualSemaphoreSample {

    public static void main(String[] args) throws InterruptedException{
        /*System.out.println("Action ... GO!");
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++){
            Thread thread = new Thread(new SemaphoreWorker(semaphore));
            thread.start();
        }*/

        Semaphore semaphore1 = new Semaphore(0);
        for (int i = 0; i < 10; i++){
            Thread thread = new Thread(new MySemaphoreWorker(semaphore1));
            thread.start();
        }
        System.out.println("Action ... GO");
        semaphore1.release(5);
        System.out.println("Wait for permits off");
        while (semaphore1.availablePermits() != 0){
            Thread.sleep(100L);
        }
        System.out.println("Action ... GO again");
        semaphore1.release(5);
    }
}
