package com.geekerstar.singleton.test;

import com.geekerstar.singleton.Singleton1;

/**
 * @author geekerstar
 * date: 2019/2/1 10:28
 * description:
 */
public class TestSingleton1 {
    public static void main(String[] args) {
        Singleton1 s = Singleton1.INSTANCE;
        System.out.println(s);

    }
}
