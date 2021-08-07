package com.geekerstar.se;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * @date 2021/7/23 13:09
 * @description
 */
public class StopWatch {

    public static void main(String[] args) throws InterruptedException {
        // 创建之后立刻计时，若想主动开始计时
        Stopwatch stopwatch = Stopwatch.createStarted();
// 创建计时器，但是需要主动调用 start 方法开始计时
// Stopwatch stopwatch = Stopwatch.createUnstarted();
// stopWatch.start();
// 模拟其他代码耗时
        TimeUnit.SECONDS.sleep(2l);

// 当前已经消耗的时间
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));;

        TimeUnit.SECONDS.sleep(2l);

// 停止计时 未开始的计时器调用 stop 将会抛错 IllegalStateException
        stopwatch.stop();
// 再次统计总耗时
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));;
// 重新开始，将会在原来时间基础计算，若想重新从 0开始计算，需要调用 stopwatch.reset()
        stopwatch.start();
        TimeUnit.SECONDS.sleep(2l);
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));
    }
}
