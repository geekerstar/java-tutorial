package com.geekerstar.wx;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * https://mp.weixin.qq.com/s?__biz=MzI3ODcxMzQzMw==&mid=2247484997&idx=1&sn=51b297dddbbba40912f71236fb297453&chksm=eb538373dc240a654e23578f267ce4a1345b25c4df49475087149b04fd5e3bace29b273376c0&mpshare=1&scene=1&srcid=1028v6MvmVwJ6Ob4jLdTlGpi#rd
 */
public class TestForkJoinTask extends RecursiveTask<Long> {

    private static final long MAX = 1000000000L;
    private static final long THRESHOLD = 1000L;
    private long start;
    private long end;

    public TestForkJoinTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        test();
        System.out.println("--------------------");
        testForkJoin();
    }

    private static void test() {
        System.out.println("test");
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 0L; i <= MAX; i++) {
            sum += i;
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    private static void testForkJoin() {
        System.out.println("testForkJoin");
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long sum = forkJoinPool.invoke(new TestForkJoinTask(1, MAX));
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if (end - start <= THRESHOLD) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long mid = (start + end) / 2;

            TestForkJoinTask task1 = new TestForkJoinTask(start, mid);
            task1.fork();

            TestForkJoinTask task2 = new TestForkJoinTask(mid + 1, end);
            task2.fork();

            return task1.join() + task2.join();
        }
    }

}
