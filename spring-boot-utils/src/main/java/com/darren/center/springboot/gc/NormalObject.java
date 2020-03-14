package com.darren.center.springboot.gc;

public class NormalObject {

    public String name;

    public NormalObject(String name) {
        this.name = name;
    }

    /**
     * 当NormalObject被回收的时候会调用这个方法，并打印这句话
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalizing obj " + name);
    }
}
