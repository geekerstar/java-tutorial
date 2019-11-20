package com.geekerstar.Java8.defaultmethod;

/**
 * @author geekerstar
 * date: 2019/3/10 20:10
 * description:
 *
 * 在接口 Formula 中，除了抽象方法 caculate 以外，还定义了一个默认方法 sqrt。Formula的实现类只需要实现抽象方法 caculate 就可以了。默认方法 sqrt 可以直接使用。
 */
public abstract class Imp implements Formula {
    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        formula.calculate(100);
        formula.sqrt(16);

    }

}
