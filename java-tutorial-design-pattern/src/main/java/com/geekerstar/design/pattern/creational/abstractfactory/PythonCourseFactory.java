package com.geekerstar.design.pattern.creational.abstractfactory;

/**
 * @author geekerstar
 * date: 2019/1/7 12:57
 * description:
 */
public class PythonCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }

    @Override
    public Article getArticle() {
        return new PythonArticle();
    }
}
