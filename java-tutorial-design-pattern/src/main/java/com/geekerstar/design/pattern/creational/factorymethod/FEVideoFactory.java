package com.geekerstar.design.pattern.creational.factorymethod;

/**
 * @author geekerstar
 * date: 2019/1/7 10:44
 * description:
 */
public class FEVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new FEVideo();
    }
}
