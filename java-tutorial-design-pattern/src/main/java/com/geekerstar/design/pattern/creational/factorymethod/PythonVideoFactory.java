package com.geekerstar.design.pattern.creational.factorymethod;

/**
 * @author geekerstar
 * date: 2019/1/7 10:40
 * description:
 */
public class PythonVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }
}
