package com.geekerstar.Java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author geekerstar
 * date: 2019-08-16 10:57
 * description:
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter","anna","mike","xenia");

//        Collections.sort(names, new Comparator<String>() {
////            @Override
////            public int compare(String o1, String o2) {
////                return o2.compareTo(o1);
////            }
////        });
        Collections.sort(names,(a,b) -> b.compareTo(a));
        System.out.println(names);


    }




}
