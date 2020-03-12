package com.darren.center.springboot.reflect;

public class LoadDifference {

    public static void main(String[] args) throws ClassNotFoundException {
        //ClassLoader c = Robot.class.getClassLoader();
        //Class c = Class.forName("com.darren.center.springboot.reflect.Robot");
        Class.forName("com.mysql.jdbc.Driver");
    }

}
