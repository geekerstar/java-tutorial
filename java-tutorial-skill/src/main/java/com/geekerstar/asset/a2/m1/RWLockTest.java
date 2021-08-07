package com.geekerstar.asset.a2.m1;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author geekerstar
 * @date 2021/7/31 09:58
 * @description
 */
public class RWLockTest {

    /**
     * 锁降级
     */
    @Test
    public void test1(){
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();

        writeLock.lock();
        System.out.println("got the write lock");
        readLock.lock();
        System.out.println("got the read lock");
    }

    /**
     * 不支持锁升级
     */
    @Test
    public void test2(){
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();

        readLock.lock();
        System.out.println("got the read lock");
        writeLock.lock();
        System.out.println("got the write lock");
    }
}
