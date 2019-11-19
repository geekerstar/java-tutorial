package com.geekerstar.stream;


import java.util.*;

import static java.util.stream.Collectors.*;

public class StreamTest13 {

    public static void main(String[] args) {
        StudentNew student1 = new StudentNew("zhangsan", 80);
        StudentNew student2 = new StudentNew("lisi", 90);
        StudentNew student3 = new StudentNew("wangwu", 100);
        StudentNew student4 = new StudentNew("zhaoliu", 90);
        StudentNew student5 = new StudentNew("zhaoliu", 90);

        List<StudentNew> students = Arrays.asList(student1, student2, student3, student4, student5);

        List<StudentNew> students1 = students.stream().collect(toList());
        students1.forEach(System.out::println);
        System.out.println("------------");

        System.out.println("count: " + students.stream().collect(counting()));
        System.out.println("count: " + students.stream().count());
        System.out.println("------------");

        students.stream().collect(minBy(Comparator.comparingInt(StudentNew::getScore))).ifPresent(System.out::println);
        students.stream().collect(maxBy(Comparator.comparingInt(StudentNew::getScore))).ifPresent(System.out::println);
        System.out.println(students.stream().collect(averagingInt(StudentNew::getScore)));
        System.out.println(students.stream().collect(summingInt(StudentNew::getScore)));
        IntSummaryStatistics intSummaryStatistics = students.stream().collect(summarizingInt(StudentNew::getScore));
        System.out.println(intSummaryStatistics);
        System.out.println("------------");

        System.out.println(students.stream().map(StudentNew::getName).collect(joining()));
        System.out.println(students.stream().map(StudentNew::getName).collect(joining(", ")));
        System.out.println(students.stream().map(StudentNew::getName).collect(joining(", ", "<begin> ", " <end>")));
        System.out.println("------------");

        Map<Integer, Map<String, List<StudentNew>>> map = students.stream().
                collect(groupingBy(StudentNew::getScore, groupingBy(StudentNew::getName)));
        System.out.println(map);
        System.out.println("------------");

        Map<Boolean, List<StudentNew>> map2 = students.stream().
                collect(partitioningBy(student -> student.getScore() > 80));
        System.out.println(map2);
        System.out.println("------------");

        Map<Boolean, Map<Boolean, List<StudentNew>>> map3 = students.stream().
                collect(partitioningBy(student -> student.getScore() > 80, partitioningBy(student -> student.getScore() > 90)));
        System.out.println(map3);
        System.out.println("------------");

        Map<Boolean, Long> map4 = students.stream().
                collect(partitioningBy(student -> student.getScore() > 80, counting()));
        System.out.println(map4);
        System.out.println("------------");

        Map<String, StudentNew> map5 = students.stream().
                collect(groupingBy(StudentNew::getName, collectingAndThen(minBy(Comparator.comparingInt(StudentNew::getScore)),
                        Optional::get)));
        System.out.println(map5);
    }
}
