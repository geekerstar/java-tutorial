package com.geekerstar.wx;

import org.junit.Test;

/**
 * @author geekerstar
 * @date 2021/7/26 11:40
 * @description final
 *
 * https://blog.csdn.net/weixin_33724659/article/details/89695696
 */
public class Test12 {

    @Test
    public void test1(){
        String hw = "hello world";

        String hello = "hello";
        final String finalWorld2 = "hello";
        final String finalWorld3 = hello;
        final String finalWorld4 = "he" + "llo";

        String hw1 = hello + " world";
        String hw2 = finalWorld2 + " world";
        String hw3 = finalWorld3 + " world";
        String hw4 = finalWorld4 + " world";

        System.out.println(hw == hw1);
        System.out.println(hw == hw2);
        System.out.println(hw == hw3);
        System.out.println(hw == hw4);

    }
}
