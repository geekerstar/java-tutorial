package com.geekerstar.step;

import org.junit.Test;


/**
 * @author geekerstar
 * date: 2019/2/1 13:33
 * description:
 */
public class TestStep2 {
    @Test
    public void test(){
        long start = System.currentTimeMillis();
        //165580141
        System.out.println(loop(100));
        long end = System.currentTimeMillis();
        //<1ms
        System.out.println(end-start);
    }

    public int loop(int n){
        if(n<1){
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if(n==1 || n==2){
            return n;
        }
        //初始化为走到第二级台阶的走法
        int one = 2;
        //初始化为走到第一级台阶的走法
        int two = 1;
        int sum = 0;

        for(int i=3; i<=n; i++){
            //最后跨2步 + 最后跨1步的走法
            sum = two + one;
            two = one;
            one = sum;
        }
        return sum;
    }
}
