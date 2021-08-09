package com.geekerstar.wx;

import org.junit.Test;

import java.util.TreeSet;

/**
 * @author geekerstar
 * @date 2021/8/9 20:19
 * @description
 *
 */
public class TestTreeSet {

    /**
     *  TreeSet集合是用来对象元素进行排序的,同样他也可以保证元素的唯一
     */
    @Test
    public void test1(){
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(3);
        ts.add(1);
        ts.add(1);
        ts.add(2);
        ts.add(2);
        ts.add(3);
        ts.add(3);
        System.out.println("TreeSet存储Integer类型的元素: " + ts);
    }

    @Test
    public void test2(){

    }
}
