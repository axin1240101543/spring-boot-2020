package com.darren.center.springboot.utils;

import com.darren.center.springboot.entity.Comsumer;
import com.darren.center.springboot.entity.Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestBlockingQueue {


    public static void main(String args[]){
        BlockingQueue blockingQueue = new LinkedBlockingQueue();
        Thread producer = new Thread(new Producer(blockingQueue));
        Thread consumer = new Thread(new Comsumer(blockingQueue));
        producer.start();
        consumer.start();
    }
}
