package com.geekerstar.se;

import org.junit.Test;

/**
 * @author geekerstar
 * @date 2021/7/23 14:05
 * @description
 */
public class DivideByZeroTest {

    @Test
    public void di(){
        System.out.println(1.0 / 0.0);
//        System.out.println(1 / 0);

        int i = 1234;
        System.out.println(i / 1000 * 1000);
        System.out.println("5+5=" + 5 + 5);
    }
}
