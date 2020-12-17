package com.geekerstar.effective;

import java.util.concurrent.*;

/**
 * @author geekerstar
 * @date 2020/12/17 16:04
 * @description
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        // 首先我们创建了一个线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        // futureTask 我们叫做线程任务，构造器的入参是 Callable
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                // 返回一句话
                return "我是子线程"+Thread.currentThread().getName();
            }
        });
        // 把任务提交到线程池中，线程池会分配线程帮我们执行任务
        executor.submit(futureTask);
        // 得到任务执行的结果
        String result = null;
        try {
            result = (String) futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("result is："+result);
    }
}
