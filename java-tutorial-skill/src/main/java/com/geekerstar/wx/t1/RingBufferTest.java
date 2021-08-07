package com.geekerstar.wx.t1;

import java.util.concurrent.Executors;

/**
 * @author geekerstar
 * @date 2021/7/24 09:31
 * @description https://mp.weixin.qq.com/s/ucEFKf-5T-Fsfr27oFHTbQ
 */
public class RingBufferTest {
    public static void main(String[] args) {
        RingBufferWheel ringBufferWheel = new RingBufferWheel( Executors.newFixedThreadPool(2));
        for (int i = 0; i < 3; i++) {
            RingBufferWheel.Task job = new Job();
            job.setKey(i);
            ringBufferWheel.addTask(job);
        }
    }

    public static class Job extends RingBufferWheel.Task{
        @Override
        public void run() {
            System.out.println("test5");
        }
    }
}
