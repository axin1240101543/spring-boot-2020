package com.darren.center.springboot.thread;

public class SyncBlockAndMethod {

    public void syncsTask(){
        synchronized (this){
            System.out.println("Hello");
            synchronized (this){
                System.out.println(" World");
            }
        }
    }

    public synchronized  void syncTask(){
        System.out.println("Hello Again");
    }

}
