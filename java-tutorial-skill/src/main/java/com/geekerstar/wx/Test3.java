package com.geekerstar.wx;

import org.junit.Test;

/**
 * @author geekerstar
 * @date 2021/7/23 18:26
 * @description https://mp.weixin.qq.com/s/59av1lGRofuaAAnhXxgu4A
 */
public class Test3 {

    @Test
    public void test1() {
        // 把一个基本数据类型转为字符串，基本数据类型.toString()是最快的方式、String.valueOf(数据)次之、数据+""最慢
        //* String.valueOf()方法底层调用了Integer.toString()方法，但是会在调用前做空判断
        //* Integer.toString()方法就不说了，直接调用了
        //* i + ""底层使用了StringBuilder实现，先用append方法拼接，再用toString()方法获取字符串
        int loopTime = 500000;
        Integer i = 0;
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++) {
            String str = String.valueOf(i);
        }
        System.out.println("String.valueOf()：" + (System.currentTimeMillis() - startTime) + "ms");
        startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++) {
            String str = i.toString();
        }
        System.out.println("Integer.toString()：" + (System.currentTimeMillis() - startTime) + "ms");
        startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++) {
            String str = i + "";
        }
        System.out.println("i + \"\"：" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
