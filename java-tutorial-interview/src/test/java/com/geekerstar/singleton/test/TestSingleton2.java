package com.geekerstar.singleton.test;

import com.geekerstar.singleton.Singleton2;

/**
 * @author geekerstar
 * date: 2019/2/1 10:30
 * description:
 */
public class TestSingleton2 {
    public static void main(String[] args) {
        Singleton2 s = Singleton2.INSTANCE;
        System.out.println(s);

    }
}
