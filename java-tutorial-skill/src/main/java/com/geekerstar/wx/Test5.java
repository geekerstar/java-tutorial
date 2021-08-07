package com.geekerstar.wx;

import java.util.concurrent.*;

/**
 * @author geekerstar
 * @date 2021/7/25 17:25
 * @description System.currentTimeMillis()性能问题
 *
 * https://mp.weixin.qq.com/s/k5iN6ItLPUkimGmvOJGZ-w
 *
 * https://mp.weixin.qq.com/s?__biz=MzAxNjk4ODE4OQ==&mid=2247486535&idx=1&sn=902b4b5a91ee3651b8cad7a2cd14d70a&chksm=9bed2d35ac9aa423eb0330340c5ca1ce3336db39fb37efff7e2e7718a7489879efaf63ac9850&mpshare=1&scene=1&srcid=&sharer_sharetime=1568040779623&sharer_shareid=535c00d0d7095600f2fcdf96cc5a31ba#rd
 */
public class Test5 {

    public static void main(String[] args) {
        int num = 10000000;
        System.out.print("单线程"+num+"次System.currentTimeMillis调用总耗时： ");
        System.out.println(singleThreadTest(() -> {
            long l = System.currentTimeMillis();
        },num));
        System.out.print("单线程"+num+"次CacheClock.currentTimeMillis调用总耗时：");
        System.out.println(singleThreadTest(() -> {
            long l = CacheClock.currentTimeMillis();
        },num));
        System.out.print("并发"+num+"次System.currentTimeMillis调用总耗时： ");
        System.out.println(concurrentTest(() -> {
            long l = System.currentTimeMillis();
        },num));
        System.out.print("并发"+num+"次CacheClock.currentTimeMillis调用总耗时： ");
        System.out.println(concurrentTest(() -> {
            long l = CacheClock.currentTimeMillis();
        },num));
    }



    /**
     * 单线程测试
     * @return
     */
    private static long singleThreadTest(Runnable runnable,int num) {
        long sum = 0;
        for (int i = 0; i < num; i++) {
            long begin = System.nanoTime();
            runnable.run();
            long end = System.nanoTime();
            sum += end - begin;
        }
        return sum;
    }

    /**
     * 并发测试
     * @return
     */
    private static long concurrentTest(Runnable runnable,int num) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(200,200,60, TimeUnit.SECONDS,new LinkedBlockingQueue<>(num));
        long[] sum = new long[]{0};
        //闭锁基于CAS实现，并不适合当前的计算密集型场景，可能导致等待时间较长
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            threadPoolExecutor.submit(() -> {
                long begin = System.nanoTime();
                runnable.run();
                long end = System.nanoTime();
                //计算复杂型场景更适合使用悲观锁
                synchronized(Test5.class) {
                    sum[0] += end - begin;
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sum[0];
    }


    /**
     * 缓存时钟，缓存System.currentTimeMillis()的值，每隔20ms更新一次
     */
    public static class CacheClock{
        //定时任务调度线程池
        private static ScheduledExecutorService timer = new ScheduledThreadPoolExecutor(1);
        //毫秒缓存
        private static volatile long timeMilis;
        static {
            //每秒更新毫秒缓存
            timer.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    timeMilis = System.currentTimeMillis();
                }
            },0,1000,TimeUnit.MILLISECONDS);
        }

        public static long currentTimeMillis() {
            return timeMilis;
        }
    }
}

