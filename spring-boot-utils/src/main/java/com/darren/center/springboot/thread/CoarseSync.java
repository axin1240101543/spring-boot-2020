package com.darren.center.springboot.thread;

/**
 * 像这种连续的append操作，就属于这类情况了，JVM会检测到这样一连串操作，
 * 都对同一个对象加上同步锁的情况，那么此时JVM就会将加锁的同步范围，
 * 粗化到整个一系列操作的外部，使整个一连串的append操作，只需要加一次锁就可以完成了。
 */
public class CoarseSync {

    public static String copyString100Times(String target){
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while(i < 100){
            sb.append(target);
        }
        return sb.toString();
    }

}
