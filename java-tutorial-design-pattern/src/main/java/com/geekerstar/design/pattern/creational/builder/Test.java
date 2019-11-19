package com.geekerstar.design.pattern.creational.builder;


public class Test {
    public static void main(String[] args) {
        CourseBuilder courseBuilder = new CourseActualBuilder();
        Coach coach = new Coach();
        coach.setCourseBuilder(courseBuilder);

        Course course = coach.makeCourse("设计模式",
                "Java设计模式PPT",
                "Java设计模式视频",
                "Java设计模式笔记",
                "Java设计模式问答");
        System.out.println(course);

    }
}
