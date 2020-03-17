package com.darren.center.springboot.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable + FutureTask实现线程的返回值
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());
        new Thread(futureTask).start();
        if (!futureTask.isDone()){
            System.out.println("task has not finished, please wait!");
        }
        System.out.println("task return: " + futureTask.get());
    }

}
