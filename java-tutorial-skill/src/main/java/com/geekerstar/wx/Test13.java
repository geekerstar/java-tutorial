package com.geekerstar.wx;

import org.junit.Test;

/**
 * @author geekerstar
 * @date 2021/7/26 11:44
 * @description
 *
 * https://mp.weixin.qq.com/s/pA0K3xOBdE-nXNxOYFbGrg
 */
public class Test13 {

    @Test
    public void test1(){
        if (true) {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
            System.out.println(placeHolder.length / 1024);
        }
        System.gc();
    }

    @Test
    public void test2(){
        if (true) {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
            System.out.println(placeHolder.length / 1024);
            placeHolder = null;
        }
        System.gc();
    }
}
