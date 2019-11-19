package com.geekerstar.design.principle.demeter;

/**
 * @author geekerstar
 * date: 2019/1/6 15:48
 * description:
 */
public class Boss {
    public void commandCheckNumber(TeamLeader teamLeader){

        teamLeader.checkNumberOfCourse();
    }
}
