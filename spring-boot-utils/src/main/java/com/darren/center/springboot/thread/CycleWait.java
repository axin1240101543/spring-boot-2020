package com.darren.center.springboot.thread;

/**
 * 使用主线程等待法或者join方法实现线程的返回值
 */
public class CycleWait implements Runnable{

    private String value;

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.value = "we have data now";
    }

    public static void main(String[] args) throws InterruptedException {
        CycleWait cw = new CycleWait();
        Thread t = new Thread(cw);
        t.start();
        /*while (null == cw.value){
            Thread.currentThread().sleep(100);
        }*/
        t.join();
        System.out.println("value: " + cw.value);
    }
}
