package com.geekerstar.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author geekerstar
 * date: 2019/11/17 22:45
 * description:
 */
public class StreamTest4 {
    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("hello", "world", "hello world");


        String[] stringArray = stream1.toArray(length -> new String[length]);
//        String[] stringArray = stream.toArray(String[]::new);
        Arrays.asList(stringArray).forEach(System.out::println);


        Stream<String> stream2 = Stream.of("hello", "world", "hello world");
        List<String> list1 = stream2.collect(Collectors.toList());
        list1.forEach(System.out::println);

        Stream<String> stream3 = Stream.of("hello", "world", "hello world");
        ArrayList<Object> list2 = stream3.collect(() -> new ArrayList<>(), (theList, item) -> theList.add(item), (theList1, theList2) -> theList1.addAll(theList2));
        list2.forEach(System.out::println);

        Stream<String> stream4 = Stream.of("hello", "world", "hello world");
        List<String> list3 = stream4.collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        list3.forEach(System.out::println);


        Stream<String> stream5 = Stream.of("hello", "world", "hello world");
        List<String> list4 = stream5.collect(Collectors.toCollection(ArrayList::new));
        list4.forEach(System.out::println);


        Stream<String> stream6 = Stream.of("hello", "world", "hello world");
        Set<String> set = stream6.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set.getClass());
        set.forEach(System.out::println);


        Stream<String> stream7 = Stream.of("hello", "world", "hello world");
        String str = stream7.collect(Collectors.joining()).toString();
        System.out.println(str);


    }
}
