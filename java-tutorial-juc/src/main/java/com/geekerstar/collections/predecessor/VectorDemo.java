package com.geekerstar.collections.predecessor;

import java.util.Vector;

/**
 * @author geekerstar
 * @date 2020/2/9 14:26
 * @description Vector，主要是看Vector源码
 */
public class VectorDemo {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("test");
        System.out.println(vector.get(0));
    }
}
