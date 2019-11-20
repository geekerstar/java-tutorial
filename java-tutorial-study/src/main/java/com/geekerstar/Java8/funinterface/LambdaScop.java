package com.geekerstar.Java8.funinterface;

/**
 * @author geekerstar
 * date: 2019-08-16 11:54
 * description:
 * 对于lambda表达式外部的变量，其访问权限的粒度与匿名对象的方式非常类似。你能够访问局部对应的外部区域的局部final变量，以及成员变量和静态变量。
 */
public class LambdaScop {
    public static void main(String[] args) {
        /**
         * 访问局部变量
         * 我们可以访问lambda表达式外部的final局部变量：
         */
        final int num = 1;
        Converter<Integer,String> stringConverter = (from) -> String.valueOf(from+num);
        System.out.println(stringConverter.convert(2));

    }
}
