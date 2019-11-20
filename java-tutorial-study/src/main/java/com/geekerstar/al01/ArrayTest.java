package com.geekerstar.al01;

/**
 * @author geekerstar
 * date: 2019/3/5 15:03
 * description:
 */
public class ArrayTest {
//    public static void main (String[]args){
//        int a = 1;
//        int b = 2;
//        System.out.println(a);
//        System.out.println(b);
//        change(a, b);
//        System.out.println(a);
//        System.out.println(b);
//
//    }
//    public static void change(int a,int b){
//        a = a + b;
//        b = b + a;
//    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5};
        System.out.println(arr[0]);
        change(arr);
        System.out.println(arr[0]);
    }

    public static void change(int[] arr) {
        arr[0] = 200;
    }
}
