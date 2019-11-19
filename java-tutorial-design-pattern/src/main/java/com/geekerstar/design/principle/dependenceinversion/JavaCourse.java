package com.geekerstar.design.principle.dependenceinversion;

/**
 * @author geekerstar
 * @date 2018/12/9
 * description
 */
public class JavaCourse implements ICourse {
    @Override
    public void studyCourse() {
        System.out.println("Geeker在学习Java课程");

    }
}
