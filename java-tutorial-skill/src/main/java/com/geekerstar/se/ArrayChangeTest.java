package com.geekerstar.se;

import org.junit.Test;

/**
 * @author geekerstar
 * @date 2021/7/23 14:09
 * @description
 */
public class ArrayChangeTest {

    @Test
    public void test1(){
        int a = 1;
        int b = 2;
        System.out.println(a);
        System.out.println(b);
        change1(a, b);
        System.out.println(a);
        System.out.println(b);
    }


    public static void change1(int a,int b){
        a = a + b;
        b = b + a;
    }

    @Test
    public void test2(){
        int[] arr = {1, 3, 5};
        System.out.println(arr[0]);
        change2(arr);
        System.out.println(arr[0]);
    }

    public static void change2(int[] arr) {
        arr[0] = 200;
    }
}
