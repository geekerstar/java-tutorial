package com.geekerstar.design.principle.singleresponsibility;

/**
 * @author geekerstar
 * date: 2019/1/6 15:18
 * description:
 */
public interface ICourse {
    String getCourseName();
    byte[] getCourseVideo();

    void studyCourse();
    void refundCourse();
}
