package com.geekerstar.Java8.defaultmethod;

/**
 * @author geekerstar
 * date: 2019/3/10 20:09
 * description:
 */
public interface Formula {
    double calculate(int a);
    default double sqrt(int a){
        return Math.sqrt(a);
    }
}
