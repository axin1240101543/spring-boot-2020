package com.darren.center.springboot.gc;

public class Finalization {

    public static Finalization finalization;

    /**
     * gc前会执行此方法
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalized");
        //回收此对象的时候，将这个对象的值再一次赋给了它的成员变量。
        finalization = this;
    }

    public static void main(String[] args) {
        Finalization f = new Finalization();
        System.out.println("first print: " + f);
        f = null;
        //GC
        System.gc();
        //休息一段时间，让上面的垃圾回收线程执行完成，因为当主线程执行完成后，将会打断finalize方法的执行
        try {
            Thread.currentThread().sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("second print：" + f);
        System.out.println(f.finalization);
    }
}
