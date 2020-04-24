package com.darren.center.springboot.collection;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger的使用
 */
public class ExchangerDemo {

    private static Exchanger exchanger = new Exchanger();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute( ()->{
            try {
                String girl = (String) exchanger.exchange("我其实暗恋你很久了……");
                System.out.println("女生说：" + girl);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute( ()->{
            try {
                System.out.println("女生慢慢从教室里走出来……");
                String boy = (String) exchanger.exchange("我很喜欢你……");
                System.out.println("男生说：" + boy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
