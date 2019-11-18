package com.geekerstar.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author geekerstar
 * date: 2019/11/18 22:34
 * description:
 */
public class StreamTest10 {
    public static void main(String[] args) {
        // 注意对比
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world hello", "hello welcome");
//        List<String[]> result = list.stream().map(item -> item.split(" ")).distinct().collect(Collectors.toList());
//        result.forEach(item -> Arrays.asList(item).forEach(System.out::println));

        List<String> result = list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        result.forEach(System.out::println);

        System.out.println("--------------------------");

        List<String> list1 = Arrays.asList("Hi", "Hello", "你好");
        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        List<String> result1 = list1.stream().flatMap(item -> list2.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList());
        result1.forEach(System.out::println);

    }
}
