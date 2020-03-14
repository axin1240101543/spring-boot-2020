package com.darren.center.springboot.gc;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ReferenceQueueTest {

    private static ReferenceQueue<NormalObject> rq = new ReferenceQueue<>();

    private static void checkQueue(){
        Reference<NormalObject> ref = null;
        //遍历ReferenceQueue
        while ((ref = (Reference<NormalObject>) rq.poll()) != null){
            if (ref != null){
                //引用对象
                System.out.println("In queue: " + ((NormalObjectWeakReference)ref).name);
                //所引用的对象
                System.out.println("reference object: " + ref.get());
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<WeakReference<NormalObject>> weakList = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            weakList.add(new NormalObjectWeakReference(new NormalObject("Weak " + i), rq));
            System.out.println("Create weak:" + weakList.get(i));
        }
        //GC前遍历一下ReferenceQueue
        System.out.println("first time");
        checkQueue();
        System.gc();
        try {
            Thread.currentThread().sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //GC前遍历一下ReferenceQueue
        System.out.println("second time");
        checkQueue();
    }


    /**
     * 1、创建了三个NormalObjectWeakReference实例
     * Create weak:com.darren.center.springboot.gc.NormalObjectWeakReference@49097b5d
     * Create weak:com.darren.center.springboot.gc.NormalObjectWeakReference@6e2c634b
     * Create weak:com.darren.center.springboot.gc.NormalObjectWeakReference@37a71e93
     * 2、第一次遍历ReferenceQueue（为空）
     * first time
     * 3、GC后，NormalObject被回收了，但NormalObjectWeakReference没有被回收，被添加到了ReferenceQueue当中了
     * finalizing obj Weak 2
     * finalizing obj Weak 1
     * finalizing obj Weak 0
     * 4、再次遍历ReferenceQueue（有值）
     * second time
     * In queue: Weak 0
     * reference object: null（所引用的对象在GC已经被回收）
     * In queue: Weak 2
     * reference object: null（所引用的对象在GC已经被回收）
     * In queue: Weak 1
     * reference object: null（所引用的对象在GC已经被回收）
     */

}
