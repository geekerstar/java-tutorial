package com.geekerstar.stream;

import java.util.IntSummaryStatistics;
import java.util.stream.Stream;

/**
 * @author geekerstar
 * date: 2019/11/18 22:06
 * description:
 */
public class StreamTest7 {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.iterate(1, item -> item + 2).limit(6);
        System.out.println("原始数据：" + stream);
        // [1, 3, 5, 7, 9, 11]

        IntSummaryStatistics summaryStatistics = stream.filter(item -> item > 2).
                mapToInt(item -> item * 2).skip(2).limit(2).summaryStatistics();

        System.out.println(summaryStatistics.getMin());
        System.out.println(summaryStatistics.getCount());
        System.out.println(summaryStatistics.getMax());


        System.out.println(stream.distinct());

    }
}
