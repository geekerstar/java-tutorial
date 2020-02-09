package com.geekerstar.immutable;

/**
 * @author geekerstar
 * @date 2020/2/9 10:51
 * @description final的方法
 */
public class FinalMethodDemo {
    public void drink() {

    }

    public final void eat() {

    }

    public static void sleep() {

    }
}

class SubClass extends FinalMethodDemo {

    @Override
    public void drink() {
        super.drink();
        eat();
    }

    //    public final void eat() {
//
//    }
    public static void sleep() {

    }
}
