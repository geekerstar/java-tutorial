## AbstractQueuedSynchronizer-AQS

![aqs.png](https://github.com/geekerstar/high-concurrency/blob/master/img/aqs.png)

- 使用Node实现FIFO队列，可以用于构建锁或者其他同步装置的基础框架
- 利用了一个Int类型表示状态
- 使用方法是继承
- 子类通过继承并通过实现它的方法管理其状态{acquire 和 release}的方法操纵状态
- 可以同时实现排它锁和共享锁模式（独占、共享）

## AQS 同步组件
- CountDownLatch
- Semaphore
- CyclicBarrier
- ReentrantLock
- Condition
- FutureTask

## Semaphore

![semaphore.png](https://github.com/geekerstar/high-concurrency/blob/master/img/semaphore.png)

## CyclicBarrier
![cyclicbarrier.png](https://github.com/geekerstar/high-concurrency/blob/master/img/cyclicbarrier1.png)

## ReentrantLock 与锁
ReentrantLock(可重入锁)和synchronized区别
- 可重入性
- 锁的实现
- 性能的区别
- 功能区别

ReentrantLock独有功能
- 可指定是公平锁还是非公平锁
- 提供了一个Condition类，可以分组唤醒需要唤醒的线程
- 提供能够中断等待锁的线程的机制，lock.lockInterruptibly()

## Fork/Join 框架
![fork.png](https://github.com/geekerstar/high-concurrency/blob/master/img/fork.png)


## Fork/Join 框架
![fork.png](https://github.com/geekerstar/high-concurrency/blob/master/img/fork.png)

## BlockingQueue
![block.png](https://github.com/geekerstar/high-concurrency/blob/master/img/block.png)

![block2.png](https://github.com/geekerstar/high-concurrency/blob/master/img/block2.png)

- ArrayBlockingQueue
- DelayQueue
- LinkedBlockingQueue
- PriorityBlockingQueue
- SynchronousQueue
