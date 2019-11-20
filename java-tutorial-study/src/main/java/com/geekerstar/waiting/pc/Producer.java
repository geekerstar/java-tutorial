package com.geekerstar.waiting.pc;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author geekerstar
 * date: 2019-07-24 09:44
 * description:
 */
public class Producer  implements Runnable{

    // true --> 生产者一直执行，false --> 停掉生产者
    private volatile boolean isRunning = true;

    // 公共资源
    private final Vector sharedQueue;

    // 公共资源的最大数量
    private final int SIZE;

    private static AtomicInteger count = new AtomicInteger();

    public Producer(Vector sharedQueue, int SIZE){
        this.sharedQueue = sharedQueue;
        this.SIZE = SIZE;
    }

    @Override
    public void run() {
        int data;
        Random r = new Random();
        System.out.println("start producer id =" + Thread.currentThread().getId());
        try {
            while (isRunning){
                //模拟延迟
                Thread.sleep(r.nextInt(1000));

                // 当队列满时阻塞等待
                while (sharedQueue.size() == SIZE){
                    synchronized (sharedQueue){
                        System.out.println("Queue is full,producer " + Thread.currentThread().getId() + " is wating, size:"+sharedQueue.size());
                        sharedQueue.wait();

                    }
                }
                // 队列不满时持续创造新元素
                synchronized (sharedQueue){
                    // 生产数据
                    data = count.incrementAndGet();
                    sharedQueue.add(data);
                    System.out.println("producer create data:" + data + ",size:"+sharedQueue.size());
                    sharedQueue.notifyAll();

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupted();
        }
    }

    public void stop(){
        isRunning = false;
    }
}
