package com.geekerstar.highconcurrency.example.immutable;

import com.geekerstar.highconcurrency.annoations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author geekerstar
 * date: 2019/1/22 09:44
 * description:
 *
 * 不可变对象
 * 不可变对象需要满足的条件
 *      1、对象创建以后其状态就不能改变
 *      2、对象所有域都是final类型
 *      3、对象是正确创建的（在对象创建期间，this引用没有逸出）
 * final关键字：类、方法、变量
 *      1、修饰类：不能被继承
 *      2、修饰方法：1、锁定方法不能被继承修改；2、效率
 *      3、修饰变量：基本数据类型变量，引用类型变量
 * Collections.unmodifiableXXX:Collection、List、Set、Map…
 * Guava：ImmutableXXX：Collection、List、Set、Map…
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
//        a = 2;
//        b = "3";
//        map = Maps.newHashMap();
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

    private void test(final int a) {
//        a = 1;
    }
}

