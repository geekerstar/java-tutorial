package com.geekerstar.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author geekerstar
 * @date 2020/2/6 19:34
 * @description newFixedThreadPool出错的情况
 */
public class FixedThreadPoolOOM {
    public static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new subTask());
        }
    }

}

class subTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
