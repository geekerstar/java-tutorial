package com.geekerstar.wx;

import org.junit.Test;

/**
 * @author geekerstar
 * @date 2021/7/25 17:49
 * @description StringBuilder和 StringBuffer
 *
 * https://blog.csdn.net/Farrell_zeng/article/details/100153345
 *
 * https://www.jianshu.com/p/469fe8fdd3be
 *
 * cnblogs.com/keatsCoder/p/13212289.html
 *
 * https://mp.weixin.qq.com/s?__biz=MzIwNTk5NjEzNw==&mid=2247488193&idx=1&sn=00f30b0f10b4d7f9714cb052a80291e9&chksm=97293bc7a05eb2d1b008571c8c45c97043b77d7eb9635f32d65a54ecdeec178b4569497a8637&mpshare=1&scene=1&srcid=&sharer_sharetime=1565780056489&sharer_shareid=535c00d0d7095600f2fcdf96cc5a31ba#rd
 */
public class Test6 {

    @Test
    public void stringBuilderTest() throws InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++){
                        stringBuilder.append("a");
                    }
                }
            }).start();
        }

        Thread.sleep(100);
        System.out.println(stringBuilder.length());
    }

    @Test
    public void stringBufferTest() throws InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++){
                        stringBuffer.append("a");
                    }
                }
            }).start();
        }

        Thread.sleep(100);
        System.out.println(stringBuffer.length());
    }




}
