package com.darren.center.springboot.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Java反射机制是在运行状态中，对于任意一个类，都能知道这个类的所有属性和方法；
 * 对于任意一个对象，都能够调用它的任意方法和属性；
 * 这种动态获取信息以及动态调用方法的功能称为java语言的反射机制。
 */
public class ReflectSample {

    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException,
            InstantiationException, InvocationTargetException, NoSuchFieldException {
        //全路径
        Class c = Class.forName("com.darren.center.springboot.reflect.Robot");
        //创建Robot的实例
        Robot robot = (Robot) c.newInstance();
        //打印类的名字
        System.out.println("Class Name is " + c.getName());
        //此时可以调用类的公共方法，例如：sayHi()  但是throwHello私有方法就不行了，这不是反射，但是反射是可以的
//        robot.sayHi("bob");
//        robot.throwHello("bob");
        //获取声明的方法，能够获取所有修饰符的方法，但不能获取继承的方法，当然也包括实现的接口的方法
        Method throwHello =  c.getDeclaredMethod("throwHello", String.class);
        //由于获取的私有的方法，所以需要将对象可访问标志设置为true，否则会报IllegalAccessException异常
        throwHello.setAccessible(true);
        //调用方法，参数1是实例，参数2是方法要传入的参数
        Object obj = throwHello.invoke(robot, "bob");
        System.out.println("throwHello result is " + obj);
        //只能获取public的方法, 能获取继承的方法，当然也包括实现的接口的方法，也不需要调用setAccessible()方法
        Method sayHi = c.getMethod("sayHi", String.class);
        sayHi.invoke(robot, "Welcome");

        //获取私有属性
        Field name =  c.getDeclaredField("name");
        //设置可访问
        name.setAccessible(true);
        //设置值
        name.set(robot, "Alice");
        //再次调用sayHi方法
        sayHi.invoke(robot, "Welcome");

        System.out.println(System.getProperty("java.class.path"));
    }

}
