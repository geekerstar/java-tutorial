package com.geekerstar.highconcurrency.example.count;

import com.geekerstar.highconcurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author geekerstar
 * date: 2019/1/21 16:04
 * description:
 *
 * 导致共享变量在线程间不可见的原因：
 *      1、线程交叉执行
 *      2、重排序结合线程交叉执行
 *      3、共享变量更新后的值没有在工作内存与主存间及时更新
 *
 * 可见性-synchronized
 * JMM关于synchronized的两条规定：
 *      线程解锁前，必须把共享变量的最新值刷新到主内存
 *      线程加锁时，将清空工作内存中共享变量的值，从而使用共享变量时需要从主内存中重新读取最新的值（注意：加锁与解锁是同一把锁）
 *
 * 可见性-volatile
 * 通过加入【内存屏障】和【禁止重排序】优化来实现
 *      1、对volatile变量写操作时，会在写操作后加入一条store屏障指令，将本地内存中的共享变量值刷新到主内存
 *      2、对volatile变量读操作时，会在读操作前加入一条load屏障指令，从主内存中读取共享变量
 *
 *
 *
 * 线程安全-有序性
 * Java内存模型中，允许编译器和处理器对指令进行重排序，但是重排序过程不会影响到单线程程序的执行，却会影响到多线程并发执行的正确性
 * volatile、synchronized、Lock
 *
 * 有序性-happens-before原则
 *      1、程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在后面的操作
 *      2、锁定规则：一个unlock操作先行发生于后面对同一个锁的Lock操作
 *      3、volatile变量规则：对一个变量的写操作先行发生于后面对这个变量的读操作
 *      4、传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出操作A先行发生于操作C
 *      5、线程启动规则：Thread对象的start()方法先行发生于此线程的每一个动作
 *      6、线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生
 *      7、线程终结规则：线程中所有的操作都先行发生于线程的终止检测、我们可以通过Thread.join()方法结束、
 *         Thread.isAlive()的返回值手段检测到线程已经终止执行
 *      8、对象终结规则：一个对象的初始化完成先行发生于他的finalize()方法的开始
 *
 * 线程安全性-总结
 *      原子性：Atomic包、CAS算法、synchronized、Lock
 *      可见性：synchronized、volatile
 *      有序性：happens-before
 */
@Slf4j
@NotThreadSafe
public class CountExample4 {

    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 200;

    public static volatile int count = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        count++;
        // 1、count
        // 2、+1
        // 3、count
    }
}

