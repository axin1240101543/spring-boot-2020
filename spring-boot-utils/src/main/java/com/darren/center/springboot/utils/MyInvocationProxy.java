package com.darren.center.springboot.utils;

import java.lang.reflect.Proxy;

public class MyInvocationProxy {

    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        MyInvocationHandler handler = new MyInvocationHandler(hello);
        Hello proxyHello = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(),
                HelloImpl.class.getInterfaces(), handler);
        proxyHello.sayHello();
    }

}
