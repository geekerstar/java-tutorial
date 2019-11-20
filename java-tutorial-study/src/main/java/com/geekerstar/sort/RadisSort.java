package com.geekerstar.sort;

/**
 * @author geekerstar
 * date: 2019/3/11 22:23
 * description:
 */
public class RadisSort {
    public static void main(String[] args){

        int[] arrays = {6, 4322, 432, 344, 55, 234, 45, 243, 5, 2, 4, 5, 6, 7, 3245, 345, 345, 234, 68, 65};

        radixSort(arrays);

        System.out.println("" + arrays);

    }

    public static void radixSort(int[] arrays){

        int max = findMax(arrays, 0, arrays.length - 1);

        //需要遍历的次数由数组最大值的位数来决定
        for (int i = 1; max / i > 0; i = i * 10) {

            int[][] buckets = new int[arrays.length][10];

            //获取每一位数字(个、十、百、千位...分配到桶子里)
            for (int j = 0; j < arrays.length; j++) {

                int num = (arrays[j] / i) % 10;

                //将其放入桶子里
                buckets[j][num] = arrays[j];
            }

            //回收桶子里的元素
            int k = 0;

            //有10个桶子
            for (int j = 0; j < 10; j++) {
                //对每个桶子里的元素进行回收
                for (int l = 0; l < arrays.length ; l++) {

                    //如果桶子里面有元素就回收(数据初始化会为0)
                    if (buckets[l][j] != 0) {
                        arrays[k++] = buckets[l][j];

                    }

                }

            }

        }
    }


    /**
     * 递归，找出数组最大的值
     *
     * @param arrays 数组
     * @param L      左边界，第一个数
     * @param R      右边界，数组的长度
     * @return
     */

    public static int findMax(int[] arrays, int L, int R){

        //如果该数组只有一个数，那么最大的就是该数组第一个值了
        if (L == R) {
            return arrays[L];
        } else {

            int a = arrays[L];
            int b = findMax(arrays, L + 1, R);//找出整体的最大值

            if (a > b) {
                return a;
            } else {
                return b;
            }
        }
    }
}
