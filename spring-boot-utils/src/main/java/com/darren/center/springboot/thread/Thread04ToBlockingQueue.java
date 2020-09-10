package com.darren.center.springboot.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 两个线程交替打印，a线程打印1，b线程打印a，然后a线程打印2，b线程打印b，如此类推。
 */
public class Thread04ToBlockingQueue {

    static BlockingQueue q1 = new ArrayBlockingQueue(1);
    static BlockingQueue q2 = new ArrayBlockingQueue(1);

    public static void main(String[] args) {
        String [] c1 = {"1","2","3","4","5","6"};
        String [] c2 = {"a","b","c","d","e","f"};
        new Thread(()->{
            for (String c:c1){
                System.out.println(c);
                try {
                    q1.put("ok");
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for (String c:c2){
                try {
                    q1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(c);
                try {
                    q2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
