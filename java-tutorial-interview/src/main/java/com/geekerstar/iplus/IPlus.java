package com.geekerstar.iplus;

/**
 * @author geekerstar
 * date: 2019/2/1 12:01
 * description:
 */
public class IPlus {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }

}
