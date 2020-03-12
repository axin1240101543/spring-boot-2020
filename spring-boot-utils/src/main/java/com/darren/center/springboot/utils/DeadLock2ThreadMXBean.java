package com.darren.center.springboot.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DeadLock2ThreadMXBean extends Thread{
    private String first;
    private String second;

    public DeadLock2ThreadMXBean(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    @Override
    public void run() {
        synchronized (first){
            System.out.println(this.getName() + " obtained:" + first);
            try {
                Thread.sleep(1000L);
                synchronized (second){
                    System.out.println(this.getName() + " obtained:" + second);
                }
            }catch (InterruptedException e){
                //do something
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
        Runnable dlcheck = () -> {
            long[] threadIds = mbean.findDeadlockedThreads();
            if (threadIds != null) {
                ThreadInfo[] threadInfos = mbean.getThreadInfo(threadIds);
                System.out.println("Detected deadlock threads:");
                for (ThreadInfo threadInfo : threadInfos) {
                    System.out.println(threadInfo.getThreadName());
                }
            }
        };
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(dlcheck, 5L, 10L, TimeUnit.SECONDS);
        String lockA = "lockA";
        String lockB = "lockB";
        DeadLock t1 = new DeadLock("Thead1", lockA, lockB);
        DeadLock t2 = new DeadLock("Thead2", lockB, lockA);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
