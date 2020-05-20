package com.darren.center.springboot.jmm;

public class VolatileDemo {

    public static int i = 0;

    public synchronized static void increase(){
        i++;
    }

    volatile boolean b;

    public void close(){
        b = true;
    }

    public void doWork(){
        while (!b){
            System.out.println("safe");
        }
    }

}
