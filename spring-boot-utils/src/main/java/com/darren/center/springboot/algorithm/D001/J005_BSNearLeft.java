package com.darren.center.springboot.algorithm.D001;

/**
 * @Description :
 * 在一个有序数组中，找>=某个数最左侧的位置
 * 举例：1111222233334444555
 * 数字：2
 * 最左侧的位置：1111(2)
 *
 * 111122223-3-334444555
 *           2
 * 11112-2-2233
 *       2
 * 11-1-122
 *    2
 *    11-2-2
 *       2
 * @Author : Darren
 * @Date : 2021 年 02 月 07 日 20:06:18
 * @since : 1.0
 */
public class J005_BSNearLeft {

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
            if (arrays[mid] >= num) {
                r = mid - 1;
                index = mid;
            } else {
                l = mid + 1;
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
        //最左侧的位置 不一定相等,也就是说不一定包含
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] >= num) {
                return i;
            }
        }
        return -1;
    }
}