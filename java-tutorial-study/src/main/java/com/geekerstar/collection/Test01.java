package com.geekerstar.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author geekerstar
 * date: 2019-07-22 09:23
 * description:
 */
public class Test01 {


    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        list.add("王二");
        list.add("麻子");
        Iterator<String> iterator = list.iterator();
        iterator.forEachRemaining((String name) -> System.out.println(name));
    }
}
