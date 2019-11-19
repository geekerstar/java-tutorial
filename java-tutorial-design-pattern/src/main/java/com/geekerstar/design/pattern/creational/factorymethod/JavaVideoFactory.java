package com.geekerstar.design.pattern.creational.factorymethod;

/**
 * @author geekerstar
 * date: 2019/1/7 10:39
 * description:
 */
public class JavaVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }
}
