package com.geekerstar.immutable;

/**
 * @author geekerstar
 * @date 2020/2/9 10:52
 * @description
 */
public class FinalStringDemo1 {
    public static void main(String[] args) {
        String a = "wukong2";
        final String b = "wukong";
        String d = "wukong";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
    }
}
