package com.geekerstar.array;

public class SelectDemo {
    public static void main(String[] args) {
        int[] arr = {4,9,32,12,6,12};
        selectSort(arr);
        printArray(arr);

    }
    // print array
    private static void printArray(int[] arr){
        for (int x = 0; x < arr.length; x++){
            if(x != arr.length -1) {
                System.out.print(arr[x] + ",");
            }else{
                System.out.println(arr[x]);

            }
        }
    }

    //selectSort
    private static void selectSort(int[] arr){
        for (int x = 0; x < arr.length - 1; x++){
            for(int y = x + 1; y<arr.length;y++){
                if(arr[x] > arr[y]){
                    int temp = arr[x];
                    arr[x] = arr[y];
                    arr[y] = temp;
                }
            }
        }
    }
}
