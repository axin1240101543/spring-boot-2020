package com.darren.center.springboot.algorithm.D001;

/**
 * @Description :
 * 冒泡排序
 *
 * 0 ~ N-1 0位置的数和后面的每个数比较，将较大值不断的往后移 N-1位置放最大值
 * 0 ~ N-2 0位置的数和后面的每个数比较，将较大值不断的往后移 N-2位置放最大值
 * 0 ~ N-3 0位置的数和后面的每个数比较，将较大值不断的往后移 N-3位置放最大值
 * 0 ~ N-i 0位置的数和后面的每个数比较，将较大值不断的往后移 N-i位置放最大值
 *  ……
 * 0 ~ 0 终止
 *
 * @Author : Darren
 * @Date : 2020 年 12 月 28 日 19:39:08
 * @since : 1.0
 */
public class J002_BubbleSort {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = J001_SelectSort.generateRandomArray(maxSize, maxValue);
            int[] var2 = J001_SelectSort.copyArray(var1);
            bubbleSort(var1);
            J001_SelectSort.comparator(var2);
            if (!J001_SelectSort.isEqual(var1, var2)){
                success = false;
                J001_SelectSort.printArray(var1);
                J001_SelectSort.printArray(var2);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static void bubbleSort(int[] arrays){
        if (arrays == null || arrays.length < 2){
            return;
        }
        //0 ~ N-1
        //0 ~ N-2
        //0 ~ N-3
        for (int i = arrays.length - 1; i >= 0; i--) {
            //内循环一次  i位置的数就排好序了
            for (int j = 0; j < i; j++) {
                if (arrays[j] > arrays[j+1]){
                    J001_SelectSort.swap(arrays, j, j+1);
                }
            }
        }
    }

}