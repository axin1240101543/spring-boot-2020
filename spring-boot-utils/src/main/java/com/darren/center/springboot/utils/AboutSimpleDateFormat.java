package com.darren.center.springboot.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

/**
 * 关于SimpleDateFormat线程安全
 * Date formats are not synchronized.
 * It is recommended to create separate format instances for each thread.
 * If multiple threads access a format concurrently, it must be synchronized externally.
 */
public class AboutSimpleDateFormat {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 定义一个线程池
     */
    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("test-thread-factory").build();
    private static ExecutorService executorService = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());



    /**
     * 1、设置时区
     * 2、测试SimpleDateFormat非线程安全类
     *
     * set的size不是100的原因：
     * F:/Java/jdk1.8.0_151/src.zip!/java/text/SimpleDateFormat.java:943中的calendar是一个成员变量
     * 当我们的private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");定义为静态变量时，多个线程都可以访问到calendar。
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("北京时间：" + simpleDateFormat.format(new Date()));
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        System.out.println("设置纽约时区后的时间：" + simpleDateFormat.format(new Date()));

        CountDownLatch countDownLatch = new CountDownLatch(100);
        Set<String> set = Collections.synchronizedSet(new HashSet<String>());//线程安全HashSet
        for(int i = 0; i < 100; i++){
            Calendar calendar = Calendar.getInstance();
            int a = i;
            executorService.execute(() -> {
                calendar.add(Calendar.DATE, a);//时间增加
                String dataStr = simpleDateFormat.format(calendar.getTime());
                set.add(dataStr);
                countDownLatch.countDown();//从100开始减
            });
        }
        countDownLatch.await();//阻塞，直到100 -> 0
        System.out.println("线程不安全：" + set.size());//不是100个。。。

        test1();

        test2();

        test3();

        test4();
    }

    /**
     * 定义ThreadLocal
     */
    private static final ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    /**
     * 解决方法1
     * 使用ThreadLocal
     * @throws InterruptedException
     */
    public static void test1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        Set<String> set = Collections.synchronizedSet(new HashSet<String>());//线程安全HashSet
        for(int i = 0; i < 100; i++){
            Calendar calendar = Calendar.getInstance();
            int a = i;
            executorService.execute(() -> {
                calendar.add(Calendar.DATE, a);//时间增加
                String dataStr = threadLocal.get().format(calendar.getTime());//从threadLocal中获取一个SimpleDateFormat对象
                set.add(dataStr);
                countDownLatch.countDown();//从100开始减
            });
        }
        countDownLatch.await();//阻塞，直到100 -> 0
        System.out.println("解决方法1:" + set.size());
    }

    /**
     * 解决方法2
     * 将SimpleDateFormat声明成局部变量
     * @throws InterruptedException
     */
    public static void test2() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        Set<String> set = Collections.synchronizedSet(new HashSet<String>());//线程安全HashSet
        for(int i = 0; i < 100; i++){
            Calendar calendar = Calendar.getInstance();
            int a = i;
            executorService.execute(() -> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//声明成局部变量
                calendar.add(Calendar.DATE, a);//时间增加
                String dataStr = simpleDateFormat.format(calendar.getTime());
                set.add(dataStr);
                countDownLatch.countDown();//从100开始减
            });
        }
        countDownLatch.await();//阻塞，直到100 -> 0
        System.out.println("解决方法2:" + set.size());
    }


    /**
     * 解决方法3
     * 使用synchronized关键字来加锁
     * @throws InterruptedException
     */
    public static void test3() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        Set<String> set = Collections.synchronizedSet(new HashSet<String>());//线程安全HashSet
        for(int i = 0; i < 100; i++){
            Calendar calendar = Calendar.getInstance();
            int a = i;
            executorService.execute(() -> {
                synchronized (simpleDateFormat){
                    calendar.add(Calendar.DATE, a);//时间增加
                    String dataStr = simpleDateFormat.format(calendar.getTime());
                    set.add(dataStr);
                    countDownLatch.countDown();//从100开始减
                }
            });
        }
        countDownLatch.await();//阻塞，直到100 -> 0
        System.out.println("解决方法3:" + set.size());
    }

    /**
     * 解决方法4
     * 使用java8的线程安全类：DateTimeFormatter
     * @throws InterruptedException
     */
    public static void test4(){
        String dateStr = LocalDateTime.now().format(dateTimeFormatter);
        System.out.println("解决方法4:" + dateStr);

        LocalDateTime localDateTime1 = LocalDateTime.parse(dateStr, dateTimeFormatter);
        System.out.println("解决方法4:" + localDateTime1);
    }
}
