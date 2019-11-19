package com.geekerstar.design.principle.singleresponsibility;

/**
 * @author geekerstar
 * date: 2019/1/6 15:22
 * description:
 */
public class CourseImpl implements ICourseManager,ICourseContent {
    @Override
    public void studyCourse() {

    }

    @Override
    public void refundCourse() {

    }

    @Override
    public String getCourseName() {
        return null;
    }

    @Override
    public byte[] getCourseVideo() {
        return new byte[0];
    }
}
