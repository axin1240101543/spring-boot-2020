package com.darren.center.springboot.algorithm.D001;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description :
 * 在一个有序数组中，判断这个数是否存在
 *
 * 1、坐标来到中间位置
 * 2、如果和这个数相等，则直接返回
 * 3、如果小于这个数，则去右边继续二分
 * 4、如果大于这个数，则去左边继续二分
 *
 * @Author : Darren
 * @Date : 2021 年 02 月 07 日 19:55:22
 * @since : 1.0
 */
public class J004_BSExist {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        int num = 8;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] arrays = J001_SelectSort.generateRandomArray(maxSize, maxValue);
            //数组排序
            J001_SelectSort.comparator(arrays);
            if ((!bsExist(arrays, num) && containNum(arrays, num))
                    || (bsExist(arrays, num) && !containNum(arrays, num))){
                success = false;
                J001_SelectSort.printArray(arrays);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static boolean bsExist(int[] arrays, int num){
        if (arrays == null || arrays.length == 0){
            return false;
        }
        int l = 0;
        int r = arrays.length - 1;
        int m = 0;
        while (l < r){
            //m = (l + r) / 2;
            //m = l + (r - l) / 2; 避免 l+r 超过 int的边界
            //m = l + ((r - l) >> 1); 位移运算比加减乘除快
            m = l + ((r - l) >> 1);
            if (arrays[m] == num){
                return true;
            }else if(arrays[m] > num){
                //num小于中间值  0 < num < m 那么r = m - 1
                r = m - 1;
            }else {
                //num大于中间值  m < num < r 那么l = m + 1
                l = m + 1;
            }
        }
        return arrays[l] == num;
    }

    /**
     * java 自带是否包含某个元素
     * @param arrays
     * @param num
     * @return
     */
    public static boolean containNum(int[] arrays, int num){
        if (arrays == null || arrays.length == 0){
            return false;
        }
        List<Integer> list = Arrays.stream(arrays).boxed().collect(Collectors.toList());
        if (list.contains(num)){
            return true;
        }
        return false;
    }

}