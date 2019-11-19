package com.geekerstar.highconcurrency.example.concurrent;

import com.geekerstar.highconcurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author geekerstar
 * date: 2019/1/22 11:03
 * description:
 *
 * 安全共享对象策略-总结
 *      1、线程限制：一个线程限制的对象，由线程独占，并且只能被占有它的线程修改
 *      2、共享只读：一个共享只读的对象，在没有任何额外同步的情况下，可以被多个线程并发访问，但是任何线程都不能修改它
 *      3、线程安全对象：一个线程安全的对象或容器，在内部通过同步机制来保证线程安全，所以其他线程无需额外的同步就可以通过公共接口随意访问它
 *      4、被守护对象：被守护对象只能通过获取特定的锁来访问
 */
@Slf4j
@ThreadSafe
public class ConcurrentHashMapExample {

    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 200;

    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", map.size());
    }

    private static void update(int i) {
        map.put(i, i);
    }
}

