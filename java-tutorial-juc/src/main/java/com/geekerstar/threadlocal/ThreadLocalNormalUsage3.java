package com.geekerstar.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author geekerstar
 * @date 2020/2/7 12:51
 * @description 1000个任务，用线程池执行
 */
public class ThreadLocalNormalUsage3 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    /**
     * 所有线程公用一个SimpleDateFormat对象发生线程安全问题，会有重复的
     */
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage3().date(finalI);
                    System.out.println(date);

                }
            });
        }
        threadPool.shutdown();

    }

    public String date(int seconds) {
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);

        return dateFormat.format(date);
    }
}
