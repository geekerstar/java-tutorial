package com.geekerstar.design.principle.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geekerstar
 * date: 2019/1/6 15:48
 * description:
 */
public class TeamLeader {
    public void checkNumberOfCourse(){
        List<Course> courseList = new ArrayList<Course>();
        for (int i = 0; i < 20; i++){
            courseList.add(new Course());
        }
        System.out.println("在线课程的数量是："+courseList.size());

    }
}
