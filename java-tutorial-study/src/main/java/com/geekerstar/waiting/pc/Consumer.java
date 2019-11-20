package com.geekerstar.waiting.pc;

import java.util.Random;
import java.util.Vector;

/**
 * @author geekerstar
 * date: 2019-07-24 09:53
 * description:
 */
public class Consumer implements Runnable {
    // 公共资源
    private final Vector sharedQueue;
    public Consumer(Vector sharedQueue) {
        this.sharedQueue = sharedQueue;
    }
    @Override
    public void run() {
        Random r = new Random();
        System.out.println("start consumer id = " + Thread.currentThread().getId());
        try {
            while (true) {
                // 模拟延迟
                Thread.sleep(r.nextInt(1000));
                // 当队列空时阻塞等待
                while (sharedQueue.isEmpty()) {
                    synchronized (sharedQueue) {
                        System.out.println("Queue is empty, consumer " + Thread.currentThread().getId()
                                + " is waiting, size：" + sharedQueue.size());
                        sharedQueue.wait();
                    }
                }
                // 队列不空时持续消费元素
                synchronized (sharedQueue) {
                    System.out.println("consumer consume data：" + sharedQueue.remove(0) + ", size：" + sharedQueue.size());
                    sharedQueue.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}

