package com.geekerstar.product;

import java.util.concurrent.BlockingQueue;

/**
 * @author geekerstar
 * date: 2019/3/4 10:30
 * description:
 */
public class Producer implements Runnable {

    BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue){
        this.queue =  queue;
    }
    @Override
    public void run() {
        try {
            String temp = "A Product，生产者：" + Thread.currentThread().getName();
            System.out.println("I have made a product：" + Thread.currentThread().getName());
            // 如果队列是满的话，会阻塞当前线程
            queue.put(temp);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
