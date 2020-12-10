package com.geekerstar.effective;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DemoThread extends Thread {

    private CountDownLatch countDownLatch;

    public DemoThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException ignore) {
            }
        }
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        DemoThread demoThread1 = new DemoThread(countDownLatch);
        DemoThread demoThread2 = new DemoThread(countDownLatch);

        demoThread1.start();
        demoThread2.start();

        countDownLatch.await();
    }

}
