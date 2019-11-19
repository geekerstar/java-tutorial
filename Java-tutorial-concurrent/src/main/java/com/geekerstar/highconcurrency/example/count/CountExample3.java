package com.geekerstar.highconcurrency.example.count;

import com.geekerstar.highconcurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author geekerstar
 * date: 2019/1/21 15:55
 * description:
 *
 *  原子性-synchronized
 *  修饰代码块：大括号括起来的代码，作用于调用的对象
 *  修饰方法：整个方法，作用于调用的对象
 *  修饰静态方法：整个静态方法，作用于所有对象
 *  修饰类：括号括起来的部分，作用于所有对象
 *
 *  原子性-对比
 *  synchronized:不可中断锁，适合竞争不激烈，可读性好
 *  Lock：可中断锁，多样化同步，竞争激烈时能维持常态
 *  Atomic:竞争激烈时能维持常态，比Lock性能好，只能同步一个值
 *
 */
@Slf4j
@ThreadSafe
public class CountExample3 {

    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private synchronized static void add() {
        count++;
    }
}

