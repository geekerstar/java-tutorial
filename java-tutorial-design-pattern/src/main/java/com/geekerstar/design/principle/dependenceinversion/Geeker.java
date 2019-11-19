package com.geekerstar.design.principle.dependenceinversion;

/**
 * @author geekerstar
 * @date 2018/12/9
 * description
 */
public class Geeker {
//    public void studyJavaCourse(){
//        System.out.println("geeker在学习Java课程");
//    }
//
//    public void studyFECourse(){
//        System.out.println("geeker在学习前端课程");
//    }
//
//    public void studyPythonCourse(){
//        System.out.println("geeker在学习Python课程");
//    }

    private ICourse iCourse;
//    public Geeker(ICourse iCourse){
//        this.iCourse = iCourse;
//    }

    public void studyCourse(){
        iCourse.studyCourse();
    }

    public void setiCourse(ICourse iCourse) {
        this.iCourse = iCourse;
    }
}
