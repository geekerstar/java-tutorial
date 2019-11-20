package com.geekerstar.array;

public class Reverse {
    public static void main(String[] args) {
        int[] arr = new int[]{4,6,22,56,21,7};
        reverseArray(arr);
        for(int i = 0; i<arr.length;i++){
            System.out.print(arr[i]+",");

        }
    }

    private static void reverseArray(int[] arr){
        for(int i = 0; i < arr.length/2; i++){
            int temp = arr[i];
            arr[i]=arr[arr.length-1-i];
            arr[arr.length - 1 -i]=temp;
        }
    }
}
