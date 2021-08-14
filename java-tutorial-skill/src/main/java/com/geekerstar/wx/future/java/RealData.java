package com.geekerstar.wx.future.java;

import java.util.concurrent.*;

/**
 * https://mp.weixin.qq.com/s?__biz=MzU2MTI4MjI0MQ==&mid=2247485096&idx=1&sn=14c76d3e9959c03127c87f01f28adf9a&chksm=fc7a6b06cb0de21061245bb3b13a128a299296afbedd9ebf5c23f9bcf13ceee1009435e7d326&mpshare=1&scene=1&srcid=#rd
 */
public class RealData implements Callable<String> {
    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        //这里是真实的业务逻辑
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "["+para+"]";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //传入RealData到FutureTask
        FutureTask<String> futureTask = new FutureTask<String>(new RealData("name"));
        //创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //在这里开启线程执行RealData的call()方法
        executorService.submit(futureTask);
        System.out.println("请求完毕 "+System.currentTimeMillis());
        //...这里进行一些其它操作
        System.out.println("数据："+futureTask.get());
        System.out.println("获取完毕 "+System.currentTimeMillis());
        //启动一个有序的关闭，之前提交的任务将被执行，但是不会接受新的任务。
        executorService.shutdown();
    }

}
