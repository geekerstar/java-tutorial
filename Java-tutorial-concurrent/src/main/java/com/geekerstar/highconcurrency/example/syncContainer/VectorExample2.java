package com.geekerstar.highconcurrency.example.syncContainer;

import com.geekerstar.highconcurrency.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * @author geekerstar
 * date: 2019/1/22 10:47
 * description: 验证vector线程不安全的情况
 */
@NotThreadSafe
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while (true) {

            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}

