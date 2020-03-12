package com.darren.center.springboot.reflect;

public class Robot {

    //验证是否执行初始化这个步骤
    static {
        System.out.println("Hello Robot");
    }

    private String name;

    public void sayHi(String helloSentence){
        System.out.println(helloSentence + " " + name);
    }

    private String throwHello(String tag){
        return "Hello " + tag;
    }

}
