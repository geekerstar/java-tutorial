package com.geekerstar.design.pattern.creational.abstractfactory;

/**
 * @author geekerstar
 * date: 2019/1/7 13:00
 * description:
 */
public class Test {
    public static void main(String[] args) {
        CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        video.produce();
    }
}
