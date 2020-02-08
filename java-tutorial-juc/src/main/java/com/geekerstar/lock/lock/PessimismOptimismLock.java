package com.geekerstar.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author geekerstar
 * @date 2020/2/8 10:27
 * @description
 */
public class PessimismOptimismLock {
    int a;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }

    public synchronized void testMethod() {
        a++;
    }

}
