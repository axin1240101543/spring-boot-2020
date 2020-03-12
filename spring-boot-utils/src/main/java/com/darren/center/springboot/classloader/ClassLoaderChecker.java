package com.darren.center.springboot.classloader;

public class ClassLoaderChecker {

    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException {
        MyClassLoader m = new MyClassLoader("C:/Users/12401/Desktop/",
                "myClassLoader");
        Class c = m.findClass("Wali");
        System.out.println(c.getClassLoader());
        System.out.println(c.getClassLoader().getParent());
        System.out.println(c.getClassLoader().getParent().getParent());
        System.out.println(c.getClassLoader().getParent().getParent().getParent());
        c.newInstance();
    }

}
