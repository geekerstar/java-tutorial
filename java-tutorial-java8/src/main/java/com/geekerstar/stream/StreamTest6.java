package com.geekerstar.stream;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author geekerstar
 * date: 2019/11/18 21:49
 * description:
 */
public class StreamTest6 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
        stream.findFirst().ifPresent(System.out::println);

        Stream.generate(UUID.randomUUID()::toString).findFirst().ifPresent(System.out::println);

        System.out.println("---------------");

        List<Integer> collect = Stream.iterate(1, item -> item + 2).limit(6).collect(Collectors.toList());
        System.out.println(collect);

        System.out.println("---------------");

        // 找出流中大于2的元素，然后将每个元素乘以2，然后忽略掉流中的前两个元素，然后再取流中的前两个元素，最后求出流中元素总和
        Stream<Integer> limit = Stream.iterate(1, item -> item + 2).limit(6);
        System.out.println(limit.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).sum());

//        limit.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).max().ifPresent(System.out::println);


    }
}
