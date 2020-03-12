package com.darren.center.springboot.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierSample {

    public static void main(String[] args) {
        //使用CyclicBarrier特有的barrierAction，当屏障被触发时，Java会自动调度该动作。
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("Action ... GO again");
            }
        });

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new CyclicWorker(barrier));
            thread.start();
        }
    }

    static class CyclicWorker implements Runnable {

        private CyclicBarrier cyclicBarrier;

        public CyclicWorker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 3; i++) {
                    System.out.println("executed");
                    cyclicBarrier.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

}
