package com.darren.center.springboot.algorithm;

public class ArrayNoRepeat {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 4, 3, 10 ,9, 10, 4, 5, 4, 9};
        //临时数组
        int[] temArr = new int[arr.length];
        int t = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.println("第" + (i+1) + "趟");
            boolean b = true;
            for (int j = i+1; j < arr.length; j++) {
                //当arr[i]（前一个元素）和arr[j]（后一个元素 i+1）相等时，b为false，内层循环break结束，外层此次循环结束，元素不会添加到temArr[]
                //当arr[i]（前一个元素）和arr[j]（后一个元素 i+1）不相等时，b为true，内层循环比较arr[i]后面的所有元素，如果没有相等的元素，那么b还是为true，将arr[i]添加到temArr[]
                System.out.println("第" + j + "次去重，" + arr[i] + "和" + arr[j] + "比较");
                if (arr[i] == arr[j]){
                    b = false;
                    break;
                }
            }
            if (b){
                System.out.println("第" + (i+1) + "趟，将元素" + arr[i] + "加入temArr[]");
                temArr[t] = arr[i];
                t++;
            }
        }

        for (int i = 0; i < temArr.length; i++) {
            if (i == 0){
                System.out.print("temArr：");
            }
            System.out.print(temArr[i] + " ");
        }
        System.out.println();
        //结果数组
        int[] resultArr = new int[t];
        //参数介绍:
        //Object src:原数组   int srcPos:从原数组的起始位置开始  Object dest:目标数组  int destPos:目标数组的开始起始位置  int length:要copy的数组的长度
        System.arraycopy(temArr, 0, resultArr, 0, t);
        for (int i = 0; i < resultArr.length; i++) {
            if (i == 0){
                System.out.print("resultArr：");
            }
            System.out.print(resultArr[i] + " ");
        }
    }

}
