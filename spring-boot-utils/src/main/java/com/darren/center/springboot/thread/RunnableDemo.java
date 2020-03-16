package com.darren.center.springboot.thread;

public class RunnableDemo {

    public static void main(String[] args) {
        MyRunable r1 = new MyRunable("Runable1");
        MyRunable r2 = new MyRunable("Runable2");
        MyRunable r3 = new MyRunable("Runable3");
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        t1.start();
        t2.start();
        t3.start();
    }

}
