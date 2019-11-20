package com.geekerstar.sort;

/**
 * @author geekerstar
 * date: 2019/3/11 17:04
 * description:
 *
 * 性质：1、时间复杂度：O(n2)  2、空间复杂度：O(1)  3、稳定排序  4、原地排序
 */
public class BubbleSort {
    public static int[] bubbleSort(int[] arr){
        if (arr == null || arr.length < 2){
            return arr;
        }
        for(int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length - i - 1; j++){
                if (arr[j + 1] < arr[j]){
                    int t = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = t;
                }
            }
        }
        return arr;
    }
}
