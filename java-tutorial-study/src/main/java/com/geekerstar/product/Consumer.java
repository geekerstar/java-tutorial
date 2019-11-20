package com.geekerstar.product;

import java.util.concurrent.BlockingQueue;

/**
 * @author geekerstar
 * date: 2019/3/4 10:35
 * description:
 */
public class Consumer implements Runnable {
    BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            //如果队列为空，会阻塞当前线程
            String temp = queue.take();
            System.out.println(temp);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
