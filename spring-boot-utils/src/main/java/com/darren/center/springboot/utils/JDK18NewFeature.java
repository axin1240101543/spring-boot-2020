package com.darren.center.springboot.utils;

import java.util.*;

/**
 * JDK1.8特性
 */
public class JDK18NewFeature {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 8, 2, 3);

        /**
         * 流 - 过滤器
         */
        System.out.println("流 - 过滤器");
        long num = list.stream().filter(a -> a > 4).count();
        System.out.println(num);

        /**
         * 截取list中的元素
         */
        System.out.println("截取list中的元素");
        list.subList(1, 5).forEach(a -> System.out.print(a + " "));
        System.out.println();

        /**
         * 遍历list
         */
        System.out.println("遍历list");
        list.forEach(a -> System.out.print(a + " "));
        System.out.println();

        /**
         * 遍历map
         */
        System.out.println("遍历map");
        Map<String, String> map = new HashMap<>(10);
        map.put("a", "1");map.put("b", "2");
        map.put("c", "3");map.put("d", "4");
        map.put("e", "5");map.put("f", "6");
        map.forEach((k,v) ->{
            System.out.print(k + ":" + v + " ");
        });
        System.out.println();

        /**
         * 排序
         */
        System.out.println("排序");
        List<String> list1 = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "8", "2", "3");
        Collections.sort(list1, (String a, String b) -> {
            return a.compareTo(b);
        });
        list1.forEach(a -> System.out.print(a + " "));
        System.out.println();

        /**
         * 排序升级版
         */
        System.out.println("排序升级版");
        Collections.sort(list1, (b, a) -> a.compareTo(b));
        list1.forEach(a -> System.out.print(a + " "));
        System.out.println();
        /**
         * 分析：
         * 1、->是lambda表达式的关键操作符
         * 2、->前面是函数参数，后面是函数体
         * 3、Thread的构造函数接收的是一个Runnable接口对象，此处是将函数当作一个接口对象传入
         * 4、关于Runnable接口的注解：@FunctionalInterface - 函数接口
         * 4.1、被@FunctionalInterface注解的接口只能有抽象方法，接口允许有实现的方法，这种实现方法是用default关键字来标记的。
         * 4.2、如果一个类型被这个注解修饰，那么编译器会要求这个类型必须满足如下条件：
         * - 这个类型必须是一个接口，而不是注解、枚举或者类；
         * - 一个函数接口的注解类型要满足要求。
         * 4.3、编译器会自动把满足function interface要求的接口自动识别为function interface，所以你才不需要对接口增加@FunctionInterface注解。
         */
        new Thread(() -> System.out.println("123456")).start();


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(30);
        integers.add(5);
        integers.add(5);
        integers.add(7);

        int max = Integer.MIN_VALUE;
        for (Integer n : integers) {
            max = Integer.max(max, n);
        }
        System.out.println(max);

        int i = integers.stream().reduce(Integer.MIN_VALUE, (a, b) -> Integer.max(a, b));
        System.out.println(i);
    }


}
