package com.geekerstar.design.pattern.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;


public class Test {
    public static void main(String[] args) {
        List<Course> courseList = new ArrayList<Course>();

        FreeCourse freeCourse = new FreeCourse();
        freeCourse.setName("SpringMVC课程");

        CodingCourse codingCourse = new CodingCourse();
        codingCourse.setName("Java设计模式 -- By Geeker");
        codingCourse.setPrice(299);

        courseList.add(freeCourse);
        courseList.add(codingCourse);

        for(Course course : courseList){
            course.accept(new Visitor());
        }

    }
}
