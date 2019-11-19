package com.geekerstar.other;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author geekerstar
 * date: 2019/11/19 20:51
 * description:
 */
public class ComparatorTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
//        Collections.sort(list, (item1, item2) -> item1.length() - item2.length());
//        Collections.sort(list, (item1, item2) -> item2.length() - item1.length());

        Collections.sort(list, Comparator.comparingInt(String::length).reversed());

        Collections.sort(list, Comparator.comparingInt((String item) -> item.length()).reversed());

        list.sort(Comparator.comparingInt(String::length).reversed());
        list.sort(Comparator.comparingInt((String item) -> item.length()).reversed());

        Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));

        Collections.sort(list, Comparator.comparingInt(String::length).
                thenComparing((item1, item2) -> item1.toLowerCase().compareTo(item2.toLowerCase())));

        Collections.sort(list, Comparator.comparingInt(String::length).
                thenComparing(Comparator.comparing(String::toLowerCase)));

        Collections.sort(list, Comparator.comparingInt(String::length).
                thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));

        Collections.sort(list, Comparator.comparingInt(String::length).reversed().
                thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));

        Collections.sort(list, Comparator.comparingInt(String::length).reversed().
                thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())).
                thenComparing(Comparator.reverseOrder()));

        System.out.println(list);
    }
}
