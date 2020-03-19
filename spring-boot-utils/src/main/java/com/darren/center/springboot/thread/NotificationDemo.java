package com.darren.center.springboot.thread;

public class NotificationDemo {

    /**
     * 有多个线程去修改，一旦线程A对它进行改动，那线程B、C、D都能够立即看到相关的改动。
     */
    private volatile boolean go = false;

    public static void main(String[] args) throws InterruptedException {
        final NotificationDemo test = new NotificationDemo();

        Runnable waitTask = new Runnable() {
            @Override
            public void run() {
                try {
                    test.shouldGo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finished Execution");
            }
        };

        Runnable notifyTask = new Runnable() {
            @Override
            public void run() {
                test.go();
                System.out.println(Thread.currentThread().getName() + " finished Execution");
            }
        };

        Thread t1 = new Thread(waitTask, "WT1");
        Thread t2 = new Thread(waitTask, "WT2");
        Thread t3 = new Thread(waitTask, "WT3");
        Thread t4 = new Thread(notifyTask, "NT");


        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(200);

        t4.start();

    }

    /**
     * 因为都是由去test调用的，所以它们必须获得test这个同步锁才能执行里面的方法
     * @throws InterruptedException
     */
    private synchronized void shouldGo() throws InterruptedException {
        while (go != true){
            System.out.println(Thread.currentThread() + " is going to wait on this object");
            wait();
            System.out.println(Thread.currentThread() + " is woken up");
        }
        go = false;
    }

    /**
     * 因为都是由去test调用的，所以它们必须获得test这个同步锁才能执行里面的方法
     */
    private synchronized void go() {
        while (go == false){
            System.out.println(Thread.currentThread() + " is going to notify all or one thread waiting on this object");
            go = true;
            //首先t1、t2、t3三个线程启动，然后执行shouldGo方法进入test的等待池，
            // 然后线程t4启动，开始执行go方法，此时go变量是等于false的，将go赋值为ture，
            // 然后随机唤醒t1、t2、t3中的一个线程，进入锁池，go方法执行完毕，打印NT finished Execution，
            // 然后被唤醒的线程重新将go赋值为false，而其余两个线程则进入了无线的等待当中，最后打印WT? finished Execution
            //notify();

            //首先t1、t2、t3三个线程启动，然后执行shouldGo方法进入test的等待池，
            // 然后线程t4启动，开始执行go方法，此时go变量是等于false的，将go赋值为ture，
            // 然后唤醒所有的线程，进入锁池去争夺锁，go方法执行完毕，打印NT finished Execution，
            // 然后被唤醒的线程有一个争夺到锁，并重新将go赋值为false，while内剩下的两个线程无法跳出循环，
            // 又打印了一次Thread[WT?,5,main] is going to wait on this object进入了无线的等待当中，
            // 争夺到锁的线程最后打印WT? finished Execution。
            notifyAll();
        }
    }


}
