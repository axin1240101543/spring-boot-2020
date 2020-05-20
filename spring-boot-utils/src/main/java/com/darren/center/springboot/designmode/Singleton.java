package com.darren.center.springboot.designmode;

/**
 * 单例模式的实现
 */
public class Singleton {

    private volatile static Singleton singleton;

    private Singleton() {
    }

    public static Singleton getSingleton(){
        if (null == singleton){
            synchronized (Singleton.class){
                if (null == singleton){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
