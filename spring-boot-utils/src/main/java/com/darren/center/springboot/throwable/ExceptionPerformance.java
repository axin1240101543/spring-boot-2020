package com.darren.center.springboot.throwable;

/**
 * 测试if和try……catch的性能
 */
public class ExceptionPerformance {

    public static void testException(String[] array){
        try {
            System.out.println(array[0]);
        }catch (NullPointerException e){
            System.out.println("array cannot be null");
        }
    }

    public static void testIf(String[] array){
        if (array != null){
            System.out.println(array[0]);
        }else {
            System.out.println("array cannot be null");
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        //testException(null);
        testIf(null);
        System.out.println("cost:" + (System.nanoTime() - startTime));
    }

}
