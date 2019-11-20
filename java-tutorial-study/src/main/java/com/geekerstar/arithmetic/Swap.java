package com.geekerstar.arithmetic;

/**
 * @author geekerstar
 * date: 2019/3/2 15:55
 * description:
 *
 * 在网易游戏的面试中出现过：要求不使用第三个数交换两个数的值，例如：a=2;b=3，不使用其他变量交换a和b的值
 */
public class Swap {
    private void swap1() {
        int a=10,b=12;

        a=b-a; //a=2;b=12
        b=b-a; //a=2;b=10
        a=b+a; //a=12;b=10
    }
    private void swap2() {
        int a=10,b=12;

        a=a+b;//a=22,b=12
        b=a-b;//a=22,b=10
        a=a-b;//a=12,b=10
    }
}
