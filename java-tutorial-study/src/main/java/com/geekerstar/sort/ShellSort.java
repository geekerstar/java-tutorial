package com.geekerstar.sort;

/**
 * @author geekerstar
 * date: 2019/3/11 17:50
 * description:
 * 性质：1、时间复杂度：O(nlogn)  2、空间复杂度：O(1)  3、非稳定排序  4、原地排序
 */
public class ShellSort {
    public static int[] shellSort(int[] arr){
        if (arr == null || arr.length < 2){
            return arr;
        }

        //对魅族间隔为h的分组进行排序，刚开始h = n/2
        for(int h = arr.length/2; h > 0; h/=2){
            //对各个局部分组进行插入排序
            for (int i = h; i < arr.length; i++){
                //将arr[i]插入到所在分组的正确位置上
                insertI(arr,h,i);
            }
        }
        return arr;
    }

    /**
     * 将arr[i]插入到所在分组的正确位置上
     * arr[i]所在的分组为 arr[i-2*h],arr[i-h],arr[i+h]
     * @param arr
     * @param h
     * @param i
     */
    private static void insertI(int[] arr, int h, int i) {
        int temp = arr[i];
        int k;
        for(k = i - h; k > 0 && temp < arr[k]; k-=h){
            arr[k + h] = arr[k];
        }
        arr[k + h] = temp;
    }



    public static void shellSort2(int[] arr){
        //增量每次都/2
        for (int step = arr.length / 2; step > 0; step /= 2) {

            //从增量那组开始进行插入排序，直至完毕
            for (int i = step; i < arr.length; i++) {

                int j = i;
                int temp = arr[j];

                // j - step 就是代表与它同组隔壁的元素
                while (j - step >= 0 && arr[j - step] > temp) {
                    arr[j] = arr[j - step];
                    j = j - step;
                }
                arr[j] = temp;
            }
        }
    }
}
