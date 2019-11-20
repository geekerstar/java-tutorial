package com.geekerstar.sort;

/**
 * @author geekerstar
 * date: 2019/3/11 16:57
 * description:
 * 性质：1、时间复杂度：O(n2)  2、空间复杂度：O(1)  3、稳定排序  4、原地排序
 */
public class InsertSort {
//    public static int[] insertSort(int[] arr) {
//        //临时遍历
//        int temp;
//        //外层循环控制需要排序的趟数（从1开始因为将第0位看成了有序数据）
//        for (int i = 1; i < arr.length; i++) {
//            temp = arr[i];
//            //如果前一位（已排序的数据）比当前数据要大，那么久进入循环比较（参考第二趟排序）
//            while (i >= 1 && arr[i - 1] > temp) {
//                //往后退一个位置，让当前数据与之前前位进行比较
//                arr[i] = arr[i - 1];
//                //不断往前，直到退出循环
//                i--;
//            }
//            //退出循环说明找到了合适的位置了，将当前数据插入合适的位置中
//            arr[i] = temp;
//        }
//    }
}
