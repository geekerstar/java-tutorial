package com.geekerstar.waiting.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geekerstar
 * date: 2019-07-24 09:58
 * description:
 *
 * 堆溢出，死循环存值，JVM就会抛出OutOfMemoryError:java heap space异常
 */
public class OOMTest {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        int i=0;
        while(true){
            list.add(new byte[5*1024*1024]);
            System.out.println("分配次数："+(++i));
        }
    }

}
