package com.darren.center.springboot.collection;

/**
 * 对比LinkedList和TreeSet
 */

import java.util.LinkedList;
import java.util.TreeSet;

public class DuplicateAndOrderTest {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add("111");
        linkedList.add("333");
        linkedList.add("222");
        linkedList.add("111");
        linkedList.add("555");
        linkedList.add("444");
        System.out.println(linkedList);
        TreeSet treeSet = new TreeSet();
        treeSet.add("111");
        treeSet.add("333");
        treeSet.add("222");
        treeSet.add("111");
        treeSet.add("555");
        System.out.println(treeSet);
    }

}
