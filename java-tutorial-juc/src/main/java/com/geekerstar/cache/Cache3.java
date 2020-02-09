package com.geekerstar.cache;

import com.geekerstar.cache.computable.Computable;
import com.geekerstar.cache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2020/2/9 19:26
 * @description 用装饰者模式，给计算器自动添加缓存功能
 */
public class Cache3<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new HashMap();

    private final Computable<A, V> c;

    public Cache3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Cache3<String, Integer> expensiveComputer = new Cache3<>(
                new ExpensiveFunction());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = expensiveComputer.compute("666");
                    System.out.println("第一次的计算结果：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = expensiveComputer.compute("666");
                    System.out.println("第三次的计算结果：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = expensiveComputer.compute("667");
                    System.out.println("第二次的计算结果：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
