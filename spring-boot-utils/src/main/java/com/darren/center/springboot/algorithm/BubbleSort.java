package com.darren.center.springboot.algorithm;

/**
 * 冒泡排序
 *
 * 稳定的排序算法：如果遇到相等的值不进行交换
 * 平均时间复杂度 O（n^2），最佳情况是 O(n)，最差情况是 O(n^2)
 * 空间复杂度O（1）
 *
 *
 * 思路：
 * 依次比较相邻的两个数，将比较小的数放前面，比较大的数放后面；
 * 1、第一次比较：首先比较第一和第二个数，将小数放前面，将大数放后面；
 * 2、比较第二和第三个数，将小数放前面，大数放后面；
 * …… ……
 * 3、如此继续，直到比较到最后两个数，将小数放前面，将大数放后面，重复步骤，直至全部排序完成；
 * 4、在上面一趟比较完成后，最后一个数一定是数组最大的一个数，所以在第二趟的时候，最后一个数是不参与比较的；
 * 5、在第二趟比较完成后，倒数第二个数也一定是数组中第二大数，所以在第三趟中，最后两个数是不参与比较的；
 * 6、以此类推，每一趟比较次数减少一次。
 *
 * 算法分析：
 * 1、由此可见：N个数字要完成排序，总共要进行N-1趟排序，每i趟排序次数为N-i次，所以可以使用双重循环语句，外层控制循环多少趟，内层控制每一趟的循环次数。
 * 2、冒泡排序的优点：每进行一次排序，就会少比较一次，因为每进行一次排序都会找出一个较大值。如下例：第一趟比较之后，排在最后的一个数一定是最大的一个数；
 * 第二趟排序的时候，只需要比较除了最后一个数以外的其他的数，同样也能找出一个最大的数排在参与第二趟比较的数后面；第三趟比较的时候，只需要比较除了最后
 * 两个数以外的其他数，以此类推……也就是说，每进行一趟比较，每一趟少比较一次，一定程度上减少了算法的量。
 */
public class BubbleSort {

    public static void main(String[] args) {
        int [] arr = {5, 8, 10, 3, 20, 8, 6, 1};
        bubbleSort(arr);
    }

    public static void bubbleSort(int[] arr){
        //边界判断
        if (arr == null || arr.length < 2){
            return;
        }
        System.out.println("趟数： " + (arr.length - 1));
        for (int i = 1; i < arr.length; i++) {
            System.out.println("第" + i + "趟");
            for (int j = 0; j < arr.length - i; j++) {
                System.out.println("第" + (j+1) + "次排序，" + arr[j] + "和" + arr[j + 1] + "比较");
                if (arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println("第"+ i +"趟排序完成结果:");
            for (int x : arr) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

}
