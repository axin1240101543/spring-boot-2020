package com.darren.center.springboot.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 两个线程交替打印，a线程打印1，b线程打印a，然后a线程打印2，b线程打印b，如此类推。
 */
public class Thread03ToAtomicInteger {

    static AtomicInteger ai = new AtomicInteger(1);

    public static void main(String[] args) {
        String [] c1 = {"1","2","3","4","5","6"};
        String [] c2 = {"a","b","c","d","e","f"};
        new Thread(()->{
            for (String c:c1){
                while (ai.get() != 1){};
                System.out.println(c);
                ai.set(2);
            }
        }).start();

        new Thread(()->{
            for (String c:c2){
                while (ai.get() != 2){};
                System.out.println(c);
                ai.set(1);
            }
        }).start();
    }

}
