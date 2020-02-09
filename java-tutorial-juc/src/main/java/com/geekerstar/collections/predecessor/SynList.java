package com.geekerstar.collections.predecessor;

import java.util.*;

/**
 * @author geekerstar
 * @date 2020/2/9 14:25
 * @description Collections.synchronizedList(new ArrayList<E>())
 */
public class SynList {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        list.add(5);
        System.out.println(list.get(0));

        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());
    }
}
