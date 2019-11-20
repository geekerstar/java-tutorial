package com.geekerstar.waiting.algorithm;


import org.junit.Test;

import java.util.Vector;
import java.util.concurrent.*;

/**
 * @author geekerstar
 * date: 2019-07-24 08:01
 * description:
 *
 * java程序，主进程需要等待多个子进程结束之后再执行后续的代码，有哪些方案可以实现？
 *
 * 这个需求其实我们在工作中经常会用到，比如用户下单一个产品，后台会做一系列的处理，为了提高效率，每个处理都可以用一个线程来执行，所有处理完成了之后才会返回给用户下单成功
 */
public class Test1 {
    /**
     * join方法
     *
     * 使用Thread的join()等待所有的子线程执行完毕，主线程在执行，thread.join()把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。比如在线程B中调用了线程A的join()方法，直到线程A执行完毕后，才会继续执行线程B。
     * @param
     * @throws InterruptedException
     */
    @Test
    public  void joinTest() throws InterruptedException {
        Vector<Thread> vector = new Vector<>();
        for(int i = 0; i < 5; i++){
            Thread childThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("子线程被执行");
                }
            });
            vector.add(childThread);
            childThread.start();
        }
        for (Thread thread : vector){
            thread.join();
        }
        System.out.println("主线程被执行");

    }

    /**
     * 等待多线程完成的CountDownLatch
     *
     * CountDownLatch是一个同步工具类，用来协调多个线程之间的同步，或者说起到线程之间的通信（而不是用作互斥的作用）。
     * CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。使用一个计数器进行实现。计数器初始值为线程的数量。当每一个线程完成自己任务后，计数器的值就会减一。当计数器的值为0时，表示所有的线程都已经完成了任务，然后在CountDownLatch上等待的线程就可以恢复执行任务。
     * CountDownLatch的用法
     * CountDownLatch典型用法1：某一线程在开始运行前等待n个线程执行完毕。将CountDownLatch的计数器初始化为n new CountDownLatch(n) ，每当一个任务线程执行完毕，就将计数器减1 countdownlatch.countDown()，当计数器的值变为0时，在CountDownLatch上 await() 的线程就会被唤醒。一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
     * CountDownLatch典型用法2：实现多个线程开始执行任务的最大并行性。注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。做法是初始化一个共享的CountDownLatch(1)，将其计数器初始化为1，多个线程在开始执行任务前首先 coundownlatch.await()，当主线程调用 countDown() 时，计数器变为0，多个线程同时被唤醒。
     * CountDownLatch的不足
     * CountDownLatch是一次性的，计数器的值只能在构造方法中初始化一次，之后没有任何机制再次对其设置值，当CountDownLatch使用完毕后，它不能再次被使用。
     *
     */
    @Test
    public void countDownLatchTest() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++){
            Thread childThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程被执行");
                    latch.countDown();

                }
            });
            childThread.start();
        }
        //阻塞当前线程的latch中的值
        latch.await();
        System.out.println("主线程被执行");

    }

    /**
     * 同步屏障CyclicBarrier
     *
     * 这里必须注意，CylicBarrier是控制一组线程的同步，初始化的参数：5的含义是包括主线程在内有5个线程，所以只能有四个子线程，这与CountDownLatch是不一样的。
     * countDownLatch和cyclicBarrier有什么区别呢，他们的区别：countDownLatch只能使用一次，而CyclicBarrier方法可以使用reset()方法重置，所以CyclicBarrier方法可以能处理更为复杂的业务场景。
     * 我曾经在网上看到一个关于countDownLatch和cyclicBarrier的形象比喻，就是在百米赛跑的比赛中若使用 countDownLatch的话冲过终点线一个人就给评委发送一个人的成绩，10个人比赛发送10次，如果用CyclicBarrier，则只在最后一个人冲过终点线的时候发送所有人的数据，仅仅发送一次，这就是区别。
     */
    @Test
    public void cyclicBarrierTest() throws BrokenBarrierException, InterruptedException {
        final CyclicBarrier barrier = new CyclicBarrier(5);
        for (int i = 0; i < 4; i++){
            Thread childThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程被执行");
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }

            });
            childThread.start();
        }
        //阻塞当前线程知道Latch中的值
        barrier.await();
        System.out.println("主线程被执行");

    }

    /**
     * yield方法（注意此种方法经过亲自试验证明并不可靠！）
     *
     * 为何yield方法会出现这样的问题？
     * 使当前线程从执行状态（运行状态）变为可执行态（就绪状态）。cpu会从众多的可执行态里选择，也就是说，当前也就是刚刚的那个线程还是有可能会被再次执行到的，并不是说一定会执行其他线程而该线程在下一次中不会执行到了。
     * Java线程中有一个Thread.yield( )方法，很多人翻译成线程让步。顾名思义，就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，让自己或者其它的线程运行。
     * 打个比方：现在有很多人在排队上厕所，好不容易轮到这个人上厕所了，突然这个人说：“我要和大家来个竞赛，看谁先抢到厕所！”，然后所有的人在同一起跑线冲向厕所，有可能是别人抢到了，也有可能他自己有抢到了。我们还知道线程有个优先级的问题，那么手里有优先权的这些人就一定能抢到厕所的位置吗? 不一定的，他们只是概率上大些，也有可能没特权的抢到了。
     * yield的本质是把当前线程重新置入抢CPU时间的”队列”(队列只是说所有线程都在一个起跑线上.并非真正意义上的队列)。
     */
    @Test
    public void yieldTest(){
        for (int i = 0; i < 5; i++){
            Thread childThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程被执行");
                }
            });
            childThread.start();
        }
        while (Thread.activeCount() > 2){
            //保证前面的线程都执行完
            Thread.yield();
        }
        System.out.println("主线程被执行");

    }

    /**
     * FutureTask可用于闭锁，类似于CountDownLatch的作用
     */
    @Test
    public void futureTaskTest(){
        MyThread myThread = new MyThread();

        //1、执行Callable方式，需要FutureTask的支持，用于接收运算结果
        FutureTask<Integer> result1 = new FutureTask<>(myThread);
        new Thread(result1).start();
        FutureTask<Integer> result2 = new FutureTask<>(myThread);
        new Thread(result2).start();
        FutureTask<Integer> result3 = new FutureTask<>(myThread);
        new Thread(result3).start();

        Integer sum;
        try {
            sum = result1.get();
            sum = result2.get();
            sum = result3.get();
            //这里获取三个sum值是为了同步，并没有实际意义
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //FutureTask可用于闭锁，类似于CountDownLatch的作用，在所有的线程没有执行完成之后这里是不会执行的
        System.out.println("主线程被执行");

    }

    class MyThread implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            Thread.sleep(1000);
            for (int i = 0; i < 10; i++){
                sum += 1;
            }
            System.out.println("子线程被执行");
            return sum;

        }
    }

    /**
     * 使用callable+future
     *
     * Callable+Future最终也是以Callable+FutureTask的形式实现的。
     * 在这种方式中调用了： Future future = executor.submit(task);
     */
    @Test
    public void callableTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> future1 = executorService.submit(task);
        Future<Integer> future2 = executorService.submit(task);
        //获取线程执行结果，用来同步
        Integer result1 = future1.get();
        Integer result2 = future2.get();
        System.out.println("主线程执行");
        executorService.shutdown();
    }


    class Task implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            System.out.println("子线程被执行");
            return sum;

        }
    }

    /**
     * 补充：
     *
     * 1）CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：
     * CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
     * 而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
     * 另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。
     * 2）Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。
     * CountDownLatch类实际上是使用计数器的方式去控制的，不难想象当我们初始化CountDownLatch的时候传入了一个int变量这个时候在类的内部初始化一个int的变量，每当我们调用countDownt()方法的时候就使得这个变量的值减1，而对于await()方法则去判断这个int的变量的值是否为0，是则表示所有的操作都已经完成，否则继续等待。
     * 实际上如果了解AQS的话应该很容易想到可以使用AQS的共享式获取同步状态的方式来完成这个功能。而CountDownLatch实际上也就是这么做的。
     */
}
