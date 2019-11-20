package com.geekerstar.singleton.test;

import com.geekerstar.singleton.Singleton3;

/**
 * @author geekerstar
 * date: 2019/2/1 10:41
 * description:
 */
public class TestSingleton3 {
    public static void main(String[] args) {
        Singleton3 s = Singleton3.INSTANCE;
        System.out.println(s);

    }
}
