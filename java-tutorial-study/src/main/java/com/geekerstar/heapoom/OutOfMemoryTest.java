package com.geekerstar.heapoom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geekerstar
 * date: 2019/3/4 11:42
 * description:
 *
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
 * -Xms20M -Xmx20M 堆最大最小容量都是20M，则限制了堆不可以扩容
 * 当堆上没有足够的空间分配对象，又不能扩容时，就抛出OOM
 */
public class OutOfMemoryTest {
    static class OOMObject{}


    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true){
            list.add(new OOMObject());
        }
    }
}
