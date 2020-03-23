package com.darren.center.springboot.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SyncThread implements Runnable{

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        if (threadName.startsWith("A")){
            async();
        }else if(threadName.startsWith("B")){
            syncObjectBlock();
        }else if(threadName.startsWith("C")){
            syncObjectMethod();
        }else if(threadName.startsWith("D")){
            syncClassBlock();
        }else if(threadName.startsWith("E")){
            syncClassMethod();
        }
    }

    /**
     * 异步方法
     */
    private void async(){
        try {
            System.out.println(Thread.currentThread().getName() + " async method start " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " async method end " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * 同步代码块
     */
    private void syncObjectBlock(){
        System.out.println(Thread.currentThread().getName() + " syncObjectBlock method " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        synchronized (this){
            try {
                System.out.println(Thread.currentThread().getName() + " syncObjectBlock method start " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " syncObjectBlock method end " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized 修饰非静态方法
     */
    private synchronized void syncObjectMethod(){
        System.out.println(Thread.currentThread().getName() + " syncObjectMethod method " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            try {
                System.out.println(Thread.currentThread().getName() + " syncObjectMethod method start " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " syncObjectMethod method end " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
    }

    /**
     * 类锁
     */
    private void syncClassBlock(){
        System.out.println(Thread.currentThread().getName() + " syncClassBlock method " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        synchronized (SyncThread.class){
            try {
                System.out.println(Thread.currentThread().getName() + " syncClassBlock method start " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " syncClassBlock method end " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized 修饰静态方法
     */
    private synchronized static void syncClassMethod(){
        System.out.println(Thread.currentThread().getName() + " syncClassMethod method " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        try {
            System.out.println(Thread.currentThread().getName() + " syncClassMethod method start " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " syncClassMethod method end " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
