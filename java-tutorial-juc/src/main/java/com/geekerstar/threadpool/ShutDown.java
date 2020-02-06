package com.geekerstar.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * @date 2020/2/6 20:03
 * @description 关闭线程池
 */
public class ShutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownTask());
        }
        Thread.sleep(1500);
        List<Runnable> runnables = executorService.shutdownNow();



//        boolean b = executorService.awaitTermination(3L, TimeUnit.SECONDS);
//        System.out.println(b);


//        System.out.println(executorService.isShutdown());
//        executorService.shutdown();
//        System.out.println(executorService.isShutdown());
//        System.out.println(executorService.isTerminated());
//        Thread.sleep(10000);
//        System.out.println(executorService.isTerminated());
//        executorService.execute(new ShutDownTask());
    }
}
class ShutDownTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());

        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断了");

        }
    }
}
