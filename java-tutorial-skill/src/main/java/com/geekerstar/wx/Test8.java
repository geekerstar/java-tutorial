package com.geekerstar.wx;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author geekerstar
 * @date 2021/7/26 09:40
 * @description SimpleDateFormat
 *
 * https://mp.weixin.qq.com/s/jwwDoYlXeAGYj-R6SFmRVA
 */
public class Test8 {

    // 创建 SimpleDateFormat 对象
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

    @Test
    public void test1(){
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 执行 10 次时间格式化
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            // 线程池执行任务
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    // 创建时间对象
                    Date date = new Date(finalI * 1000);
                    // 执行时间格式化并打印结果
                    System.out.println(simpleDateFormat.format(date));
                }
            });
        }
    }

    /**
     * 将 SimpleDateFormat 定义为局部变量；
     */
    @Test
    public void test2(){
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 执行 10 次时间格式化
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            // 线程池执行任务
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    // 创建 SimpleDateFormat 对象
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    // 创建时间对象
                    Date date = new Date(finalI * 1000);
                    // 执行时间格式化并打印结果
                    System.out.println(simpleDateFormat.format(date));
                }
            });
        }
        // 任务执行完之后关闭线程池
        threadPool.shutdown();
    }

    /**
     * ② 使用synchronized加锁
     */
    @Test
    public void test3(){
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 执行 10 次时间格式化
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            // 线程池执行任务
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    // 创建时间对象
                    Date date = new Date(finalI * 1000);
                    // 定义格式化的结果
                    String result = null;
                    synchronized (simpleDateFormat) {
                        // 时间格式化
                        result = simpleDateFormat.format(date);
                    }
                    // 打印结果
                    System.out.println(result);
                }
            });
        }
        // 任务执行完之后关闭线程池
        threadPool.shutdown();
    }

    /**
     * ③ 使用Lock加锁
     */
    @Test
    public void test4(){
// 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 创建 Lock 锁
        Lock lock = new ReentrantLock();
        // 执行 10 次时间格式化
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            // 线程池执行任务
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    // 创建时间对象
                    Date date = new Date(finalI * 1000);
                    // 定义格式化的结果
                    String result = null;
                    // 加锁
                    lock.lock();
                    try {
                        // 时间格式化
                        result = simpleDateFormat.format(date);
                    } finally {
                        // 释放锁
                        lock.unlock();
                    }
                    // 打印结果
                    System.out.println(result);
                }
            });
        }
        // 任务执行完之后关闭线程池
        threadPool.shutdown();
    }

    /**
     * ④ 使用ThreadLocal
     */
    // 创建 ThreadLocal 对象，并设置默认值（new SimpleDateFormat）
    private static ThreadLocal<SimpleDateFormat> threadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss"));

    @Test
    public void test5(){
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 执行 10 次时间格式化
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            // 线程池执行任务
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    // 创建时间对象
                    Date date = new Date(finalI * 1000);
                    // 格式化时间
                    String result = threadLocal.get().format(date);
                    // 打印结果
                    System.out.println(result);
                }
            });
        }
        // 任务执行完之后关闭线程池
        threadPool.shutdown();
    }

    /**
     * ⑤ 使用DateTimeFormatter
     */
    // 创建 DateTimeFormatter 对象
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss");
    @Test
    public void test6(){
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 执行 10 次时间格式化
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            // 线程池执行任务
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    // 创建时间对象
                    Date date = new Date(finalI * 1000);
                    // 将 Date 转换成 JDK 8 中的时间类型 LocalDateTime
                    LocalDateTime localDateTime =
                            LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                    // 时间格式化
                    String result = dateTimeFormatter.format(localDateTime);
                    // 打印结果
                    System.out.println(result);
                }
            });
        }
        // 任务执行完之后关闭线程池
        threadPool.shutdown();
    }
}
