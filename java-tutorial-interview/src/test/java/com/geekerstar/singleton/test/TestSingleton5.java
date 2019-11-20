package com.geekerstar.singleton.test;

import com.geekerstar.singleton.Singleton5;

import java.util.concurrent.*;

/**
 * @author geekerstar
 * date: 2019/2/1 10:49
 * description:
 */
public class TestSingleton5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Singleton5> c = new Callable<Singleton5>() {
            public Singleton5 call() throws Exception {
                return Singleton5.getInstance();
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton5> f1 = es.submit(c);
        Future<Singleton5> f2 = es.submit(c);

        Singleton5 s1 = f1.get();
        Singleton5 s2 = f2.get();

        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);

        es.shutdown();
    }
}

