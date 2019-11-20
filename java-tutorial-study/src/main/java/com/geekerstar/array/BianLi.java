package com.geekerstar.array;

public class BianLi {
    public static void main(String[] args) {
        int[] arr = new int[]{4,8,68,42,2,7};
        print(arr);
    }

    private static void print(int[] arr){
        for (int i = 0; i<arr.length; i++){
            System.out.print(arr[i]+",");

        }
    }
}
