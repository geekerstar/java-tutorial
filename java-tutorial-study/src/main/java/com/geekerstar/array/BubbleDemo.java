package com.geekerstar.array;

public class BubbleDemo {
    public static void main(String[] args) {
        int[] arr = {12,9,32,23,6,34};
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }

    // printArray
    private static void printArray(int[] arr){
        for (int x = 0; x < arr.length; x++){
            if (x != arr.length - 1){
                System.out.print(arr[x] + ",");

            }else{
                System.out.println(arr[x]);

            }
        }
    }

    private static void swap(int[] arr ,int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //Bubble
    private static void bubbleSort(int[] arr){
        for(int x = 0; x < arr.length - 1; x++){
            for(int y = 0; y < arr.length -1 -x; y++){
                if (arr[y] > arr[y+1]){
                    swap(arr,y,y+1);
                }
            }
        }
    }
}
