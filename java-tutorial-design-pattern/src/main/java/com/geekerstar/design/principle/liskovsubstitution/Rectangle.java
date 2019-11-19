package com.geekerstar.design.principle.liskovsubstitution;

/**
 * @author geekerstar
 * date: 2019/1/6 16:23
 * description:
 */
public class Rectangle implements Quadrangle {
    private long length;
    private long width;

//    public long getLength() {
//        return length;
//    }
//
//    public void setLength(long length) {
//        this.length = length;
//    }
//
//    public long getWidth() {
//        return width;
//    }
//
//    public void setWidth(long width) {
//        this.width = width;
//    }

    @Override
    public long getWidth() {
        return width;
    }

    @Override
    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setWidth(long width) {
        this.width = width;
    }
}
