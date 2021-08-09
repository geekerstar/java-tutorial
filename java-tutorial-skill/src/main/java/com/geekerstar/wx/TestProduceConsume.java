package com.geekerstar.wx;

/**
 * https://mp.weixin.qq.com/s?__biz=MzU0MzYxNjQ1Ng==&mid=2247483775&idx=1&sn=37d04efd81d9e26318d9d4dc481e994b&chksm=fb09fdd8cc7e74ce03e85b876242b6af00f011068668345088159d41cda0d075d1e1fa95778f&mpshare=1&scene=1&srcid=#rd
 */
public class TestProduceConsume {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p1 = new Producer(clerk);
        Consumer c1 = new Consumer(clerk);
        Thread t1 = new Thread(p1);// 生产者线程1
        Thread t2 = new Thread(c1);// 消费者线程1
        Thread t3 = new Thread(p1); // 生产者线程2
        t1.setName("生产者1");
        t2.setName("消费者");
        t3.setName("生产者2");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Producer implements Runnable { // 生产者
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    public void run() {
        System.out.println("生产者开始生产产品！");
        while (true) {
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            clerk.addProduct();
        }
    }
}

class Consumer implements Runnable { // 消费者
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    public void run() {
        System.out.println("消费者开始购买产品！");
        while (true) {
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }
}

class Clerk { // 店员 类似共享数据
    private int product;

    public synchronized void addProduct() {
        if (product >= 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            product++;
            notifyAll();
            System.out.println(Thread.currentThread().getName() + ":生产"
                    + product);
        }
    }

    public synchronized void consumeProduct() {
        if (product <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + ":消费"
                    + product);
            product--;
            notifyAll();
        }
    }
}
