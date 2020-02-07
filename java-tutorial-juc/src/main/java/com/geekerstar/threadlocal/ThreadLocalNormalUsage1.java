package com.geekerstar.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author geekerstar
 * @date 2020/2/7 12:51
 * @description 十个线程打印日期
 */
public class ThreadLocalNormalUsage1 {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage1().date(finalI);
                    System.out.println(date);

                }
            }).start();
            Thread.sleep(100);
        }

    }
    public String date(int seconds){
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
