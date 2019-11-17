package com.geekerstar.stream;

import java.util.stream.IntStream;

/**
 * @author geekerstar
 * date: 2019/11/17 22:34
 * description:
 */
public class StreamTest2 {
    public static void main(String[] args) {
        IntStream.of(new int[]{1,2,3,4}).forEach(System.out::println);
        System.out.println("------------");

        // 左闭右开
        IntStream.range(3,8).forEach(System.out::println);
        System.out.println("------------");

        // 左闭右闭
        IntStream.rangeClosed(3,8).forEach(System.out::println);

    }
}
