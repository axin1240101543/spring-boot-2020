package com.darren.center.springboot.enums;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public enum ReadWriteLockEnum {

    INSTANCE;

    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    public Lock writeLock(){
        return lock.writeLock();
    }

}
