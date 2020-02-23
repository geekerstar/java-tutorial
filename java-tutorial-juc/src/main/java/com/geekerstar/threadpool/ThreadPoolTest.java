package com.geekerstar.threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author geekerstar
 * @date 2020/2/23 20:03
 * @description
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);


        ExecutorService executorService = Executors.newFixedThreadPool(5);

        System.out.println("主线程名为："+Thread.currentThread().getName());

        executorService.submit(() -> {
            try {
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("No.1完成了检查。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        executorService.submit(() -> {
            try {
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("No.2完成了检查。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        executorService.submit(() -> {
            try {
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("No.3完成了检查。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        executorService.submit(() -> {
            try {
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("No.4完成了检查。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        executorService.submit(() -> {
            try {
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("No.5完成了检查。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        System.out.println("等待子线程执行完");
        countDownLatch.await();
        System.out.println("子任务执行完了，现在是主线程执行，线程名为"+Thread.currentThread().getName());

    }

}
