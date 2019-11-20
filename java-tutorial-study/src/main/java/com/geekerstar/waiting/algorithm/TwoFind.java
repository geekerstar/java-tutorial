package com.geekerstar.waiting.algorithm;

/**
 * @author geekerstar
 * date: 2019-07-24 10:14
 * description:
 */
public class TwoFind {
    public static int rank(int goal,int[] data){
        int start = 0;
        int end = data.length - 1;
        while (start <= end){
            int mid = start + (end - start)/2;
            if (goal < data[mid]){
                end = mid - 1;
            }else if (goal > data[mid]){
                start = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
