package com.geekerstar.test;


import com.sun.tools.javac.util.List;

import java.util.Collection;
import java.util.function.Function;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

/**
 * @author geekerstar
 * @date 2020/3/5 17:35
 * @description https://www.v2ex.com/t/647259#reply18
 */
public class CountTest {
    public static void main(String[] args) {
        Sample s1 = new Sample();
        s1.setUuid(1);
        s1.setDate("2-10");
        s1.setHour(18);
        s1.setClicks(1);
        s1.setViews(2);

        Sample s2 = new Sample();
        s2.setUuid(1);
        s2.setDate("2-10");
        s2.setHour(19);
        s2.setClicks(2);
        s2.setViews(2);

        Sample s3 = new Sample();
        s3.setUuid(1);
        s3.setDate("2-19");
        s3.setHour(8);
        s3.setClicks(3);
        s3.setViews(2);

        Sample s4 = new Sample();
        s4.setUuid(2);
        s4.setDate("2-11");
        s4.setHour(18);
        s4.setClicks(10);
        s4.setViews(2);

        Sample s5 = new Sample();
        s5.setUuid(2);
        s5.setDate("2-11");
        s5.setHour(19);
        s5.setClicks(20);
        s5.setViews(2);

        Collection<Sample> list = List.of(s1, s2, s3, s4, s5)
                .stream()
                .collect(collectingAndThen(toMap((Sample s) -> s.getUuid() + "_" + s.getDate(),
                        Function.identity(),
                        (Sample s, Sample a) -> {
                            int sumClick = s.getClicks() + a.getClicks();
                            int sumView = s.getViews() + a.getViews();
                            Sample result = new Sample();
                            result.setUuid(s.getUuid());
                            result.setDate(s.getDate());
                            result.setClicks(sumClick);
                            result.setViews(sumView);
                            return result;
                        }), s -> s.values()));
        list.forEach(System.out::println);
    }
}
