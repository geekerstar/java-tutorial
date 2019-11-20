package com.geekerstar.sort;


/**
 * @author geekerstar
 * date: 2019/3/11 17:08
 * description:
 */
public class BubbleSort2 {
    public static int[] bubbleSort(int[] arr){
        if (arr == null || arr.length < 2){
            return arr;
        }
        //外层循环是排序的趟数
        for (int i = 0; i < arr.length; i++){
            //每比较一趟就重新初始化为0
            boolean flag = true;
            //内层循环是当前趟数需要比较的次数
            for (int j = 0; j < arr.length - i - 1; j++){
                //前一位与后一位比较，如果前一位比后一位大，那么交换
                if (arr[j] > arr[j+1]){
                    flag = false;
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
            //一趟下来是否发生未知交换
            if (true){
                break;
            }
        }
        return arr;
    }
}
