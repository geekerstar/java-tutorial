package com.geekerstar.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author geekerstar
 * @date 2020/2/7 12:51
 * @description 两个线程打印日期
 */
public class ThreadLocalNormalUsage0 {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage0().date(10);
                System.out.println(date);

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage0().date(1007);
                System.out.println(date);

            }
        }).start();
    }

    public String date(int seconds) {
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
