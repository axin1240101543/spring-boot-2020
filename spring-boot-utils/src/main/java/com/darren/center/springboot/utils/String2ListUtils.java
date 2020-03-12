package com.darren.center.springboot.utils;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * String、List操作类
 */
public class String2ListUtils {

    private static final String SEPARATOR = ",";

    /**
     * string转list
     */
    public static void string2List(){
        String str = "a,b,c";
        String[] strArray = str.split(",");
        List<String> list = Arrays.asList(strArray);
        list.forEach(a -> System.out.print("string2List:" + a));
    }

    /**
     * list转string[]
     */
    public static void list2StringArray(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        String[] strArray = list.toArray(new String[list.size()]);
        for (String str:strArray){
            System.out.print(str);
        }
    }


    /**
     * list转string
     * 使用commons.lang3的工具类StringUtils
     * <dependency>
     *  <groupId>org.apache.commons</groupId>
     *  <artifactId>commons-lang3</artifactId>
     *  <version>3.4</version>
     * </dependency>
     */
    public static void list2String0(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        String str = StringUtils.join(list, SEPARATOR);
        System.out.print("list2String0:" + str);
    }

    /**
     * list转string
     *
     * 使用google.guava的工具类Joiner
     * <dependency>
     *  <groupId>com.google.guava</groupId>
     *  <artifactId>guava</artifactId>
     *  <version>23.0</version>
     * </dependency>
     */
    public static void list2String1(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        String str = Joiner.on(",").join(list);
        System.out.print("list2String1:" + str);
    }

    /**
     * list转string
     *
     * 使用JDK 1.8的新api
     */
    public static void list2String2(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        String str = String.join(",", list);
        System.out.print("list2String2:" + str);
    }

    /**
     * list转string
     * JDK1.7使用的老办法
     */
    public static void list2String3(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++){
            sb.append(list.get(i)).append(SEPARATOR);
        }
        System.out.print("list2String3:" + sb.toString().substring(0, sb.toString().length() - 1));
    }

    /**
     * list转string
     * JDK1.7使用的老办法
     */
    public static void list2String4(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++){
            if (i == list.size() - 1){
                sb.append(list.get(i));
            }else{
                sb.append(list.get(i)).append(SEPARATOR);
            }
        }
        System.out.print("list2String4:" + sb.toString());
    }

    /**
     * list转string
     * JDK1.7使用的老办法
     */
    public static void list2String5(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++){
            sb.append(list.get(i));
            if (i < list.size() - 1){
                sb.append(SEPARATOR);
            }
        }
        System.out.print("list2String5:" + sb.toString());
    }

    /**
     * list转string
     * JDK1.7使用的老办法
     */
    public static void list2String6(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        StringBuilder sb = new StringBuilder();
        for (String str:list){
            if (str != null && !"".equals(str)){
                sb.append(str).append(SEPARATOR);
            }
        }
        System.out.print("list2String6:" + sb.toString());
    }

    public static void main(String[] args){
        string2List();
        System.out.println("");
        list2StringArray();
        System.out.println("");
        list2String0();
        System.out.println("");
        list2String1();
        System.out.println("");
        list2String2();
        System.out.println("");
        list2String3();
        System.out.println("");
        list2String4();
        System.out.println("");
        list2String5();
        System.out.println("");
        list2String6();
    }

}
