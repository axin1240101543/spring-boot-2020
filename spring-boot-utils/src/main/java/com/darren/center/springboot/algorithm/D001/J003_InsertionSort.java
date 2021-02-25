package com.darren.center.springboot.algorithm.D001;

/**
 * @Description :
 * 插入排序
 *
 * 0 ~ 0 想做到有序 默认有序
 * 0 ~ 1 想做到有序 1位置往前看  小则交换
 * 0 ~ 2 想做到有序 2位置往前看  小则交换
 * 0 ~ i 想做到有序 i位置往前看  小则交换
 * ……
 * 0 ~ N-1 想做到有序 N-1位置往前看 小则交换
 *
 * @Author : Darren
 * @Date : 2021 年 02 月 07 日 19:29:30
 * @since : 1.0
 */
public class J003_InsertionSort {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = J001_SelectSort.generateRandomArray(maxSize, maxValue);
            int[] var2 = J001_SelectSort.copyArray(var1);
            insertionSort(var1);
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

    public static void insertionSort(int[] arrays){
        if (arrays == null || arrays.length < 2){
            return;
        }
        // 0 ~ 0 想做到有序 默认有序
        // 0 ~ 1 想做到有序 1位置往前看  小则交换
        // 0 ~ 2 想做到有序 2位置往前看  小则交换
        // 0 ~ i 想做到有序 i位置往前看  小则交换
        for (int i = 1; i < arrays.length; i++) {
            //i=1 j=0 -> 0位置和1位置比较 0~1位置有序
            //i=2 j=1 -> 1位置和2位置比较 0位置和1位置比较 0~2位置有序
            //i=3 j=2 -> 2位置和3位置比较 1位置和2位置比较 0位置和1位置比较 0~3位置有序
            //将j+1插入到合适的位置
            for (int j = i - 1; j >= 0; j--) {
                if (arrays[j] > arrays[j+1]){
                    J001_SelectSort.swap(arrays, j, j+1);
                }
            }
        }
    }
}
