package com.geekerstar.design.principle.demeter;

/**
 * @author geekerstar
 * date: 2019/1/6 15:53
 * description:
 */
public class Test {
    public static void main(String[] args) {
        Boss boss = new Boss();
        TeamLeader teamLeader = new TeamLeader();
        boss.commandCheckNumber(teamLeader);
    }
}
