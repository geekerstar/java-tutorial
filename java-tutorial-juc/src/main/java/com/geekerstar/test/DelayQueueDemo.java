package com.geekerstar.test;

import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {
    // 队列消息的生产者
    static class Product implements Runnable {
        private final BlockingQueue queue;

        public Product(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println("begin put");
                long beginTime = System.currentTimeMillis();
                queue.put(new DelayedDTO(System.currentTimeMillis() + 2000L, beginTime));//延迟 2 秒执行
                queue.put(new DelayedDTO(System.currentTimeMillis() + 5000L, beginTime));//延迟 5 秒执行
                queue.put(new DelayedDTO(System.currentTimeMillis() + 1000L * 10, beginTime));//延迟 10 秒执行
                System.out.println("end put");
            } catch (InterruptedException e) {
                System.out.println("" + e);
            }
        }
    }

    // 队列的消费者
    static class Consumer implements Runnable {
        private final BlockingQueue queue;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println("Consumer begin");
                ((DelayedDTO) queue.take()).run();
                ((DelayedDTO) queue.take()).run();
                ((DelayedDTO) queue.take()).run();
                System.out.println("Consumer end");
            } catch (InterruptedException e) {
                System.out.println("" + e);
            }
        }
    }

    @Data
    // 队列元素，实现了 Delayed 接口
    static class DelayedDTO implements Delayed {
        Long s;
        Long beginTime;

        public DelayedDTO(Long s, Long beginTime) {
            this.s = s;
            this.beginTime = beginTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(s - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        public void run() {
            System.out.println("现在已经过了" + (System.currentTimeMillis() - beginTime) / 1000 + "秒钟");
        }
    }

    // demo 运行入口
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue q = new DelayQueue();
        Product p = new Product(q);
        Consumer c = new Consumer(q);
        new Thread(c).start();
        new Thread(p).start();
    }
}
