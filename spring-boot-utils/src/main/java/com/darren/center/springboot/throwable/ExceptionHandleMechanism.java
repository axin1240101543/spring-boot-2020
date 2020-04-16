package com.darren.center.springboot.throwable;

/**
 * 异常处理原则
 */
public class ExceptionHandleMechanism {

    public static void doWork(){
        try {
            int i = 10 / 0;
            System.out.println("i=" + i);
        }catch (ArithmeticException ae){
            System.out.println("ArithmeticException:" + ae);
        }finally {
            System.out.println("finally");
        }
    }

    public static void main(String[] args) {
        doWork();
        System.out.println("Mission Complete");
    }
}
