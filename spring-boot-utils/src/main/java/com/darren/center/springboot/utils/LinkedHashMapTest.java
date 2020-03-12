package com.darren.center.springboot.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {

    public static void main(String[] args) {
        /**
         * // 实现自定义删除策略，当集合大小大于3，自动将最不常被访问的对象释放，否则行为就和普遍 Map 没有区别。
         */
        LinkedHashMap<String, String> map = new LinkedHashMap<String,String>(16, 0.75F, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > 3;
            }
        };
        //放入数据
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        map.forEach((k,v) ->{
            System.out.print(k + ":" + v + " ");
        });
        System.out.println();
        //模拟访问
        map.get("b");
        map.get("b");
        map.get("c");
        map.forEach((k,v) ->{
            System.out.print(k + ":" + v + " ");
        });
        System.out.println();
        //触发删除
        map.put("d", "4");
        map.forEach((k,v) ->{
            System.out.print(k + ":" + v + " ");
        });
    }

}
