package com.geekerstar.design.principle.dependenceinversion;

/**
 * @author geekerstar
 * @date 2018/12/9
 * description
 */
public class Test {
    //v1
//    public static void main(String[] args) {
//        Geeker geeker = new Geeker();
//        geeker.studyJavaCourse();
//        geeker.studyFECourse();
//    }

    //v2 接口方法注入
//    public static void main(String[] args) {
//        Geeker geeker = new Geeker();
//        geeker.studyCourse(new JavaCourse());
//        geeker.studyCourse(new FECourse());
//        geeker.studyCourse(new PythonCourse());
//    }

    //v3
//    public static void main(String[] args) {
//        Geeker geeker = new Geeker(new JavaCourse());
//        geeker.studyCourse();
//    }

    public static void main(String[] args) {
        Geeker geeker = new Geeker();
        geeker.setiCourse(new JavaCourse());
        geeker.studyCourse();

        geeker.setiCourse(new FECourse());
        geeker.studyCourse();
    }
}
