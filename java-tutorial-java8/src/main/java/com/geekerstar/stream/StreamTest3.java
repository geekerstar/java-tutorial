package com.geekerstar.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author geekerstar
 * date: 2019/11/17 22:40
 * description:
 */
public class StreamTest3 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(2 + 4 + 6 + 8 + 10 + 12);

        Integer reduce = list.stream().map(i -> 2 * i).reduce(0, Integer::sum);
        System.out.println(reduce);
    }
}
