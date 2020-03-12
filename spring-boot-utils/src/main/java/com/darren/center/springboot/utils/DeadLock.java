package com.darren.center.springboot.utils;

public class DeadLock extends Thread{

    private String first;
    private String second;

    public DeadLock(String name, String first, String second) {
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
