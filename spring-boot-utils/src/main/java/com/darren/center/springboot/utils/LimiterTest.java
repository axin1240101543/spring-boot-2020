package com.darren.center.springboot.utils;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.CountDownLatch;


/**
 * 限速测试
 * 使用google guava中包含了一个基于令牌桶的限速器RateLimiter
 * @author Darren
 *
 */
public class LimiterTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		//获取限速器
		final RateLimiter rateLimiter = RateLimiter.create(100);//一秒处理多少个请求
		//多个线程
		int maxNum= 500;
		//用户主线程统计时间
		final CountDownLatch countDownLatch = new CountDownLatch(maxNum);
		
		long startTime = System.currentTimeMillis();
		//模拟多线程限速
		for(int i = 0; i < maxNum; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					rateLimiter.acquire();
					System.out.println("run_Task:" + Thread.currentThread().getName());
					countDownLatch.countDown();
				}
			});
			thread.setName("thread_" + i);
			thread.start();
		}
		//获取整个过程执行时间
		countDownLatch.await();
		System.out.println("end takeTime:" + (System.currentTimeMillis() - startTime));
	}

}
