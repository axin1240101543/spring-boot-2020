package com.darren.center.springboot.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable + 线程池实现线程的返回值
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        if (!future.isDone()){
            System.out.println("task has not finished, please wait!");
        }
        try {
            System.out.println("task return: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            //必须关闭线程池
            executorService.shutdown();
        }
    }
}
