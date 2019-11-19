package com.geekerstar.design.principle.singleresponsibility;

/**
 * @author geekerstar
 * date: 2019/1/6 15:10
 * description:
 */
public class Bird {
    public void mainMoveMode(String birdName){
        if ("鸵鸟".equals(birdName)){
            System.out.println(birdName+"用脚走");
        } else {
            System.out.println(birdName+"用翅膀飞");
        }
    }
}
