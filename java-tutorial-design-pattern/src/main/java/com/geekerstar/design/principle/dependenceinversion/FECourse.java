package com.geekerstar.design.principle.dependenceinversion;

/**
 * @author geekerstar
 * @date 2018/12/9
 * description
 */
public class FECourse implements ICourse {
    @Override
    public void studyCourse() {
        System.out.println("geeker在学习前端课程");

    }
}
