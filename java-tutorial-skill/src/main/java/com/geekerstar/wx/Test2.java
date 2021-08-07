package com.geekerstar.wx;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.*;

/**
 * @author geekerstar
 * @date 2021/7/23 17:27
 * @description
 *
 * https://mp.weixin.qq.com/s?__biz=MzIwMzY1OTU1NQ==&mid=2247486613&idx=1&sn=dada4a22098b19de923cdacb6a446091&chksm=96cd4cd9a1bac5cfb42c6027bf7510e675941393ee46ec9345c76b2aacfec5a4a80b17ffc536&mpshare=1&scene=1&srcid=#rd
 */
public class Test2 {

    @Test
    public void test1() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(i);
            set.remove(i - 1);
        }

        System.out.println(set.size());
    }


    @Test
    public void test2() {
        Set<Short> set = new HashSet<>();
        for (short i = 0; i < 100; i++) {
            set.add(i);
            set.remove(i - 1);
        }
        System.out.println(set.size());
    }

    @Test
    public void test3() {
        Object i = 1 == 1 ? new Integer(3) : new Float(1);
        System.out.println(i);
    }

    @Test
    public void test4() {
        // id, name
        Map<Long, String> map = new HashMap<>();
        map.put(1L, "1111");
        map.put(2L, "2222");
        map.put(3L, "3333");

        // 后面需要调用的一个方法入参是long[] 类型，
        // map.keySet()可以拿到id的列表，toArray方法可以转化数组
        // ArrayUtils.toPrimitive方法可以把对象数据转化为基本类型数组
        // 然后，我就这样写了
//        long[] ids = ArrayUtils.toPrimitive((Long[]) map.keySet().toArray());
//        System.out.println(Arrays.toString(ids));

        long[] ids = ArrayUtils.toPrimitive(map.keySet().toArray(new Long[0]));
        System.out.println(Arrays.toString(ids));
    }

}
