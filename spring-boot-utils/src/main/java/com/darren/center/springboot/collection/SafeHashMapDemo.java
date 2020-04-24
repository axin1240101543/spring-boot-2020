package com.darren.center.springboot.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * synchronizedMap的使用
 */
public class SafeHashMapDemo {

    public static void main(String[] args) {
        Map hashmap = new HashMap();
        Map safeHashmap = Collections.synchronizedMap(hashmap);
        safeHashmap.put("a", "1");
        safeHashmap.put("b", "2");
        System.out.println(safeHashmap.get("b"));
    }

}
