package com.geekerstar.effective;


import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * @date 2020/12/10 15:05
 * @description
 */
public class StopWatchDemo {

    public static void main(String[] args) {
        StopWatch stopWatch = StopWatch.createStarted();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(stopWatch.getTime());
    }
}
