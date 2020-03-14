package com.darren.center.springboot.gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 为什么要使用WeakReference？
 * 因为WeakReference会在垃圾回收被触发的时候被回收掉，对于我们来说是可控的。
 */
public class NormalObjectWeakReference extends WeakReference<NormalObject>{

    public String name;

    public NormalObjectWeakReference(NormalObject referent, ReferenceQueue<NormalObject> q) {
        super(referent, q);
        //将NormalObject的name赋值给NormalObjectWeakReference的name
        this.name = referent.name;
    }

    /**
     * 当NormalObjectWeakReference被回收的时候会调用这个方法，并打印这句话
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing NormalObjectWeakReference " + name);
    }
}
