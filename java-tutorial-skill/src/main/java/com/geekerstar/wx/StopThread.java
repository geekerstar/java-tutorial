package com.geekerstar.wx;

import java.util.concurrent.TimeUnit;

/**
 * https://mp.weixin.qq.com/s?__biz=MzI5NTYwNDQxNA==&mid=2247484356&idx=1&sn=576a9e16961781786af46b1cfcacb5a1&chksm=ec505a15db27d30309351cce839bf6782bfc059d432eb51469d590d57fad89d5926fac4c4fa2&mpshare=1&scene=1&srcid=1029KlNCcQ8yZXPBwcHw8qJq#rd
 */
public class StopThread {

    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested) {
                    i++;
                    System.out.println(""+i);
                }
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }

}
