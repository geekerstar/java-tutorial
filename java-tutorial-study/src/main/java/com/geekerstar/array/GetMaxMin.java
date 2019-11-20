package com.geekerstar.array;

public class GetMaxMin {
    public static void main(String[] args) {
        int[] arr = new int[]{4,8,99,63,34,65};
        int max = getMax(arr);
        System.out.println(max);

    }

    private static int getMax(int[] arr){
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        return max;
    }
}
