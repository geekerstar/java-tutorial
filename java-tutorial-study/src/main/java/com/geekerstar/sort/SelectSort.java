package com.geekerstar.sort;

/**
 * @author geekerstar
 * date: 2019/3/11 16:50
 * description:
 * 1、时间复杂度：O(n2)  2、空间复杂度：O(1)  3、非稳定排序  4、原地排序
 */
public class SelectSort {
    public static int[] selectSort(int[] arr){
        //外层循环控制需要排序的趟数
        for(int i = 0; i < arr.length - 1; i++){
            //新的趟数，角标重新赋值为0
            int min = i;
            //内层循环控制遍历数组的个数并得到最大数的角标
            for (int j = i + 1; j < arr.length; j++){
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            //交换
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return arr;
    }
}
