package com.geekerstar.innerclass;

/**
 * @author geekerstar
 * date: 2019/3/10 19:46
 * description:
 */
public class Main{
    /**
     * 怎么创建静态内部类和非静态内部类的实例
     * @param args
     */
    public static void main(String[] args) {
        //创建静态内部类的实例（注意前面还是要加外部类的名字）
        OuterClass.NestedStaticClass printer = new OuterClass.NestedStaticClass();

        //调用静态内部类的非静态方法
        printer.printMessage();

        //为了创建非静态内部类，我们需要外部类的实例
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();

        //调用非静态内部类的非静态方法
        inner.dispaly();

        //我们也可结合以上步骤，一步创建内部类的实例
        OuterClass.InnerClass innerObject = new OuterClass().new InnerClass();

        //同样我们现在可以调用内部类方法
        innerObject.dispaly();
    }
}
