package com.geekerstar.design.principle.openclose;

/**
 * @author geekerstar
 * @date 2018/12/9
 * description
 */
public class Test {
    public static void main(String[] args) {
        ICourse iCourse = new JavaDiscountCourse(23,"java基础",333d);
        JavaDiscountCourse javaCourse = (JavaDiscountCourse)iCourse;
        System.out.println("课程ID："+javaCourse.getId()+"  课程名称："+javaCourse.getName()+" 课程原价"+javaCourse.getOriginPrice()+"  课程折后价格："+javaCourse.getPrice()+"元");

    }
}
