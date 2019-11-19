package com.geekerstar.highconcurrency.example.atomic;

import com.geekerstar.highconcurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author geekerstar
 * date: 2019/1/21 15:31
 * description: AtomicReference
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        // 2
        count.compareAndSet(0, 2);
        // no
        count.compareAndSet(0, 1);
        // no
        count.compareAndSet(1, 3);
        // 4
        count.compareAndSet(2, 4);
        // no
        count.compareAndSet(3, 5);
        log.info("count:{}", count.get());
    }
}
