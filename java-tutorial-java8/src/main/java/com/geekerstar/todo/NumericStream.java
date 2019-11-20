package com.geekerstar.todo;

import java.util.stream.IntStream;

public class NumericStream {
    public static void main(String[] args) {
//        Stream<Integer> stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7});
//
////        Stream<Integer> integerStream = stream.filter(i -> i.intValue() > 3);
//
////        Integer result =integerStream.reduce(0,Integer::sum);
//
////        IntStream intStream = stream.mapToInt(i -> i.intValue());
//
//        int result = stream.mapToInt(i -> i.intValue()).filter(i -> i>3).sum();
////        intStream.filter(i ->i>3).reduce(0,(i,j)->i+j);
//
//        System.out.println(result);

        int a = 9;
        //1...100
        //result int [a,b,c];

        IntStream.rangeClosed(1, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed().map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}).forEach(r -> System.out.println("a=" + r[0] + ",b=" + r[1] + ",c=" + r[2]));

        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b=" + r[1] + ",c=" + r[2]));

    }
}
