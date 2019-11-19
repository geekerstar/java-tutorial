package com.geekerstar.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author geekerstar
 * date: 2019/11/18 22:15
 * description:
 */
public class StreamTest8 {
    public static void main(String[] args) {
        // 首字母转大写
        List<String> list = Arrays.asList("hello", "world", "hello world");
        list.stream().map(item -> item.substring(0, 1).toUpperCase() + item.substring(1)).forEach(System.out::println);
        list.stream().mapToInt(String::length).filter(length -> length == 5).findFirst().ifPresent(System.out::println);


        System.out.println("-------------");

        IntStream.iterate(0, i -> (i + 1) % 2).limit(6).distinct().forEach(System.out::println);

    }
}
