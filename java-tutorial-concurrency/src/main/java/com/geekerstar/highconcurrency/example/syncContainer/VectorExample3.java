package com.geekerstar.highconcurrency.example.syncContainer;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author geekerstar
 * date: 2019/1/22 10:48
 * description: 遍历集合，做remove操作
 */
public class VectorExample3 {

    /**
     * java.util.ConcurrentModificationException
     * @param v1
     */
    private static void test1(Vector<Integer> v1) { // foreach
        for(Integer i : v1) {
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    /**
     * java.util.ConcurrentModificationException
     * @param v1
     */
    private static void test2(Vector<Integer> v1) { // iterator
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    /**
     * success
     * @param v1
     */
    private static void test3(Vector<Integer> v1) { // for
        for (int i = 0; i < v1.size(); i++) {
            if (v1.get(i).equals(3)) {
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {

        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test3(vector);
    }
}

