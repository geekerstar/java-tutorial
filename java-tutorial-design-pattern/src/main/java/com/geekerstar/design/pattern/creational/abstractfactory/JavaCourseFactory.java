package com.geekerstar.design.pattern.creational.abstractfactory;

/**
 * @author geekerstar
 * date: 2019/1/7 12:53
 * description:
 */
public class JavaCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

    @Override
    public Article getArticle() {
        return new JavaArticle();
    }
}
