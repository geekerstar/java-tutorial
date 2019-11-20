package com.geekerstar.innerclass;

/**
 * @author geekerstar
 * date: 2019/3/10 19:56
 * description:
 *
 * 匿名内部类
 * 1、匿名内部类一定是在new后面，这个匿名内部类必须继承一个父类或者实现一个接口
 * 2、匿名内部类不能有构造函数
 * 3、只能创建匿名内部类的一个实例
 * 4、Java8之前，如果匿名内部类需要访问外部类的局部变量，必须用final来修饰外部类的局部变量，Java8取消这个限制
 */
public class Test {

    public static void main(String[] args) {
        Person p = new Person() {
            @Override
            public void eat() {
                System.out.println("eat");

            }
        };
        p.eat();
    }
}
