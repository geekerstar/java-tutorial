package com.geekerstar.stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamMatch {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7});
        boolean matched = stream.allMatch(i -> i > 10);
        System.out.println(matched);

        stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7});
        matched = stream.anyMatch(i -> i>6);
        System.out.println(matched);


    }
}
