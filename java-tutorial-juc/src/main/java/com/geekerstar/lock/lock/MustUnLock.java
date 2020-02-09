package com.geekerstar.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author geekerstar
 * @date 2020/2/8 09:44
 * @description Lock不会像Synchronized一样，异常的时候自动释放锁，所以最佳实践是finally里面释放锁，以保证发生异常的时候锁一定被释放
 */
public class MustUnLock {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            // 获取本锁保护的资源
            System.out.println(Thread.currentThread().getName() + "开始执行任务");

        } finally {
            lock.unlock();
        }
    }
}
