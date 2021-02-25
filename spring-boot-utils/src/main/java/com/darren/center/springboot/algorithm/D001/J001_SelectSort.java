package com.darren.center.springboot.algorithm.D001;

import java.util.Arrays;

/**
 * @Description :
 * 选择排序
 *
 * 数据规模：N
 * 0 ~ N-1 （看 + 比） + 交换
 * 1 ~ N-1 （看 + 比） + 交换
 * 2 ~ N-1 （看 + 比） + 交换
 * 3 ~ N-1 （看 + 比） + 交换
 * i ~ N-1 （看 + 比） + 交换
 * ...
 * N-1 ~ N-1（看 + 比） + 交换
 *
 * 0 ~ N-1 （2） + 1
 * 1 ~ N-1 （2） + 1
 * 2 ~ N-1 （2） + 1
 * 3 ~ N-1 （2） + 1
 * i ~ N-1 （2） + 1
 * ...
 * N-1 ~ N-1（2） + 1
 *
 * 2 *（N-1 + N-2 + N-3 + ...） + N
 * = 2 * （aN^2 + bN + c） + N
 * = 2 * N^2 + 2bN + 2c + N
 * = O(N^2)
 * 舍弃低阶项和常数项
 *
 * @Author : Darren
 * @Date : 2020 年 12 月 28 日 19:39:08
 * @since : 1.0
 */
public class J001_SelectSort {

    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < count; i++) {
            int[] var1 = generateRandomArray(maxSize, maxValue);
            int[] var2 = copyArray(var1);
            selectSort(var1);
            comparator(var2);
            if (!isEqual(var1, var2)){
                success = false;
                printArray(var1);
                printArray(var2);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static void selectSort(int[] arrays){
        if (arrays == null || arrays.length < 2){
            return;
        }
        // 0 ~ N-1  找到最小值，在哪，放到0位置上
        // 1 ~ n-1  找到最小值，在哪，放到1 位置上
        // 2 ~ n-1  找到最小值，在哪，放到2 位置上
        for (int i = 0; i < arrays.length - 1; i++){
            int minIndex = i;
            // i ~ N-1 上找最小值的下标，与当前的最小值minIndex替换
            for (int j = i + 1; j < arrays.length; j++){
                minIndex = arrays[j] < arrays[minIndex] ? j : minIndex;
            }
            swap(arrays, i, minIndex);
        }
    }

    public static void swap(int[] arrays, int i, int j) {
        int tmp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = tmp;
    }

    /**
     * 生成一个随机数组
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue){
        //Math.random() [0,1)
        //Math.random() * N [0,N)
        //(int)(Math.random() * N) [0,N-1]
        int[] arrays = new int[(int) ((maxSize+1) * Math.random())];
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = (int) ((maxValue+1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arrays;
    }

    /**
     * 生成一个随机数
     * @param maxValue
     * @return
     */
    public static int generateRandom(int maxValue){
        return (int) ((maxValue+1) * Math.random()) - (int)(maxValue * Math.random());
    }

    /**
     * java自带排序
     * @param arrays
     */
    public static void comparator(int[] arrays){
        Arrays.sort(arrays);
    }

    /**
     * java自带复制数组
     * @param arrays
     * @return
     */
    public static int[] copyArray(int[] arrays){
        return Arrays.copyOf(arrays, arrays.length);
    }

    /**
     * 判断两个数组是否相等
     * @param var1
     * @param var2
     * @return
     */
    public static boolean isEqual(int[] var1, int[] var2){
        if ((var1 == null && var2 != null) || (var1 != null && var2 == null)){
            return false;
        }
        if(var1 == null && var2 == null){
            return true;
        }
        if (var1.length != var2.length){
            return false;
        }
        for (int i = 0; i < var1.length; i++) {
            if (var1[i] != var2[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * 打印数组
     * @param arrays
     */
    public static void printArray(int[] arrays){
        if (arrays == null){
            return;
        }
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + " ");
        }
        System.out.println();
    }
}