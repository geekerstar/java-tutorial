package com.geekerstar.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author geekerstar
 * date: 2019/11/17 22:22
 * description: 创建流的三种方式
 */
public class StreamTest1 {
    public static void main(String[] args) {
        System.out.println("----1----");
        Stream<String> stream1 = Stream.of("hello", "world", "hello world");
        stream1.forEach(System.out::println);

        System.out.println("----2.1----");
        String[] arrays = {"hello", "world", "hello world"};
        Stream<String> stream2 = Stream.of(arrays);
        stream2.forEach(System.out::println);
        System.out.println("----2.2----");
        Stream<String> stream3 = Arrays.stream(arrays);
        stream3.forEach(System.out::println);

        System.out.println("----3----");
        List<String> list = Arrays.asList(arrays);
        Stream stream4 = list.stream();
        stream4.forEach(System.out::println);
    }
}
