package com.darren.center.springboot.thread;

/**
 * 根据获取锁的分类:
 * 1、获取锁对象的两种用法
 * 同步代码块（synchronized（this），synchronized（类实例对象）），锁是小括号（）中的实例对象。
 * 在获取了这些对象的锁之后，去执行接下来的花括号里面的内容，锁就是我们小括号中的实例对象。
 * 2、获取类锁的两种用法
 * 同步代码块（synchronized（类.class）），锁是小括号（）中的类对象（Class对象）。
 * 同步静态方法（synchronized static method），锁是当前对象的类对象（Class对象）。
 */
public class SyncDemo {

    public static void main(String[] args) {
        SyncDemo syncDemo = new SyncDemo();
        //syncDemo.sameObject();
        //syncDemo.diffObject();
        //syncDemo.sameClass();
        syncDemo.diffClass();
    }

    /**
     * 同一个对象
     * synchronized method和synchronized（this）它们锁的是同一个对象，也就是this对象。
     */
    private void sameObject(){
        SyncThread syncThread = new SyncThread();
        Thread A1 = new Thread(syncThread, "A1");
        Thread A2 = new Thread(syncThread, "A2");
        Thread B1 = new Thread(syncThread, "B1");
        Thread B2 = new Thread(syncThread, "B2");
        Thread C1 = new Thread(syncThread, "C1");
        Thread C2 = new Thread(syncThread, "C2");
        A1.start();
        A2.start();
        B1.start();
        B2.start();
        C1.start();
        C2.start();
    }

    /**
     * 不同的对象
     * 线程访问不同的对象的synchronized的代码块或者synchronized method都是异步的，
     * 同一个类的不同对象的对象锁是互不干扰的。
     */
    private void diffObject(){
        Thread A1 = new Thread(new SyncThread(), "A1");
        Thread A2 = new Thread(new SyncThread(), "A2");
        Thread B1 = new Thread(new SyncThread(), "B1");
        Thread B2 = new Thread(new SyncThread(), "B2");
        Thread C1 = new Thread(new SyncThread(), "C1");
        Thread C2 = new Thread(new SyncThread(), "C2");
        A1.start();
        A2.start();
        B1.start();
        B2.start();
        C1.start();
        C2.start();
    }

    /**
     * 在同一对象的情况下，类锁表现出来的行为和对象锁表现出来的行为是一致的。
     */
    private void sameClass(){
        SyncThread syncThread = new SyncThread();
        Thread A1 = new Thread(syncThread, "A1");
        Thread A2 = new Thread(syncThread, "A2");
        Thread D1 = new Thread(syncThread, "D1");
        Thread D2 = new Thread(syncThread, "D2");
        Thread E1 = new Thread(syncThread, "E1");
        Thread E2 = new Thread(syncThread, "E2");
        A1.start();
        A2.start();
        D1.start();
        D2.start();
        E1.start();
        E2.start();
    }

    /**
     * class也是一种对象锁，只是比较特殊，所有的实例共享同一个类，也就是同一个class对象，因此它的对象锁和同一对象的行为是一样的。
     */
    private void diffClass(){
        Thread A1 = new Thread(new SyncThread(), "A1");
        Thread A2 = new Thread(new SyncThread(), "A2");
        Thread D1 = new Thread(new SyncThread(), "D1");
        Thread D2 = new Thread(new SyncThread(), "D2");
        Thread E1 = new Thread(new SyncThread(), "E1");
        Thread E2 = new Thread(new SyncThread(), "E2");
        A1.start();
        A2.start();
        D1.start();
        D2.start();
        E1.start();
        E2.start();
    }

}
