package com.darren.center.springboot.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * 两个线程交替打印，a线程打印1，b线程打印a，然后a线程打印2，b线程打印b，如此类推。
 *
 * 线程之间的通信。
 */
public class Thread01ToLockSupport {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        String [] c1 = {"1","2","3","4","5","6"};
        String [] c2 = {"a","b","c","d","e","f"};

        t1 = new Thread(()->{
            for (String c:c1){
                System.out.println(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(()->{
            for (String c:c2){
                LockSupport.park();//先阻塞
                System.out.println(c);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
    }

}
