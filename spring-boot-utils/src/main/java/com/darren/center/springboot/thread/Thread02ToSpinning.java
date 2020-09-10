package com.darren.center.springboot.thread;

/**
 * 两个线程交替打印，a线程打印1，b线程打印a，然后a线程打印2，b线程打印b，如此类推。
 */
public class Thread02ToSpinning {

    enum ReadyToRun {T1, T2};
    static volatile ReadyToRun r = ReadyToRun.T1;


    public static void main(String[] args) {
        String [] c1 = {"1","2","3","4","5","6"};
        String [] c2 = {"a","b","c","d","e","f"};
        new Thread(()->{
            for (String c:c1){
                while (r != ReadyToRun.T1){};
                System.out.println(c);
                r = ReadyToRun.T2;
            }
        }).start();

        new Thread(()->{
            for (String c:c2){
                while (r != ReadyToRun.T2){};
                System.out.println(c);
                r = ReadyToRun.T1;
            }
        }).start();
    }

}
