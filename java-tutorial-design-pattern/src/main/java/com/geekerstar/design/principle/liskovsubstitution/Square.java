package com.geekerstar.design.principle.liskovsubstitution;

/**
 * @author geekerstar
 * date: 2019/1/6 16:24
 * description:
 */
public class Square implements Quadrangle {
    private long sideLength;

//    public long getSideLength() {
//        return sideLength;
//    }
//
//    public void setSideLength(long sideLength) {
//        this.sideLength = sideLength;
//    }
//
//    @Override
//    public long getLength() {
//        return getSideLength();
//    }
//
//    @Override
//    public void setLength(long length) {
//        setSideLength(length);
//    }
//
//    @Override
//    public long getWidth() {
//        return getSideLength();
//    }
//
//    @Override
//    public void setWidth(long width) {
//        setSideLength(width);
//    }


    public long getSideLength() {
        return sideLength;
    }

    public void setSideLength(long sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public long getWidth() {
        return sideLength;
    }

    @Override
    public long getLength() {
        return sideLength;
    }
}
