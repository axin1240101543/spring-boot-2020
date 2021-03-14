package com.darren.center.springboot.algorithm.D001;

/**
 * @Description :
 * 在一个有序数组中，找<=某个数最右侧的位置
 * 举例：1111222233334444555
 * 数字：4
 * 最右侧的位置：(2)33334444555
 *
 * 111122223-3-334444555
 *           4
 * 3334-4-44555
 *      4
 * 44-4-555
 *    4
 *
 * @Author : Darren
 * @Date : 2021 年 02 月 07 日 20:06:18
 * @since : 1.0
 */
public class J006_BSNearRight {

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
            if (bsNearLeft(arrays, num) != bsNearLeftByIntrinsic(arrays, num)){
                success = false;
                J001_SelectSort.printArray(arrays);
                break;
            }
        }
        System.out.println(success ? "success" : "failure");
    }

    public static int bsNearLeft(int[] arrays, int num){
        int l = 0;
        int r = arrays.length - 1;
        int index = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arrays[mid] <= num) {
                l = mid + 1;
                index = mid;
            } else {
                r = mid - 1;
            }
        }
        return index;

    }

    /**
     * java 自带判断一个数在数组中出现的第一个位置的下标
     * @param arrays
     * @param num
     * @return
     */
    public static int bsNearLeftByIntrinsic(int[] arrays, int num){
        //长流boxed()返回一个由该流的元素组成的Stream，每个元素都装箱成Integer。
        /*List<Integer> list = Arrays.stream(arrays).boxed().collect(Collectors.toList());
        return list.indexOf(num);*/

        //误解
        //最右侧的位置 不一定相等,也就是说不一定包含
        for (int i = arrays.length - 1; i >= 0; i--) {
            if (arrays[i] <= num) {
                return i;
            }
        }
        return -1;
    }
}