package com.geekerstar.wx;

/**
 * @author geekerstar
 * @date 2021/8/13 13:25
 * @description
 *
 * https://mp.weixin.qq.com/s?__biz=MzI3ODcxMzQzMw==&mid=2247487757&idx=1&sn=3b5cd693431458064612edef399491f7&chksm=eb53943bdc241d2d1520213a0bac31e457b4f888f3fe4f3034c019b758a179202d8a602f2e5c&mpshare=1&scene=1&srcid=#rd
 */
public class TestWaitNotify {

    public static void main(String[] args) {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 20; i++) {
                    System.out.println(i);
                    if (i == 10) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1111");
                lock.notifyAll();
            }
        });

        t1.start();
        t2.start();
    }
}
