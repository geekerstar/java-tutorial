package com.geekerstar.array;

public class TwoSearch {
    public static void main(String[] args) {
        int[] arr = {19,32,123,1,23,34,12};
        int index = binarySearch(arr,23);
        System.out.println("index=" + index);

    }
    //二分查找。前提：数组必须是有序的。
    // 思路： 1，通过角标先获取中间角标上元素。
    // 2，让该元素和要找的 数据比较。
    // 3，如果要找的数大了，缩小范围，要找的范围应该是中间的角标+1---尾角标。 如果要找的数小了，要找的范围 头角标 ---中间角标-1；
    // 4，不断如此重复，就可以找到元素对应的角标。

    private static int binarySearch(int[] arr, int key){
        int max,min,mid;
        min = 0;
        max = arr.length -1;
        while (min<=max){
            mid = (min + max) >> 1;
            if(key > arr[mid])
                min = mid +1;
            else if(key < arr[mid])
                max = mid -1;
            else
                return mid;
        }
        return -1;
    }
}
