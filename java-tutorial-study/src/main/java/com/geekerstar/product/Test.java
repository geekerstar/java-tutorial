package com.geekerstar.product;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author geekerstar
 * date: 2019/3/4 10:37
 * description:
 */
public class Test {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingDeque<>(2);
        //BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        //不设置的话，LinkedBlockingQueue默认大小为Integer.MAX_VALUE
        //BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);

        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);
        for (int i = 0; i < 5; i++){
            new Thread(producer,"Producer" + (i + 1)).start();
            new Thread(consumer,"Consumer" + (i + 1)).start();

        }
    }
}
