package com.geekerstar.wx.interrupt;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * @date 2021/8/11 11:00
 * @description
 *
 * Thread#isInterrupted：测试线程是否是中断状态，执行后不更改状态标志
 * Thread#interrupted：测试线程是否是中断状态，执行后将中断标志更改为false
 */
public class TestInterrupt {

    @Test
    public void testInterrupt() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
            }
        });
        thread.start();
        TimeUnit.MICROSECONDS.sleep(100);
        thread.interrupt();
        // true
        System.out.println(thread.isInterrupted());
        // true
        System.out.println(thread.isInterrupted());
        // true
        System.out.println(thread.isInterrupted());
    }

    @Test
    public void testInterrupt2() {
        Thread.currentThread().interrupt();
        // true
        System.out.println(Thread.interrupted());
        // false
        System.out.println(Thread.interrupted());
        // false
        System.out.println(Thread.interrupted());
    }

}
