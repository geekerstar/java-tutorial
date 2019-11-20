package com.geekerstar.ji05;

public class jpp {
    public static void main(String[] args) {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            j = j++;
        }
        System.out.println(j);
    }
}

//Java采用了中间缓存变量的机制，所以,j=j++可以缓存temp=j;j=j+1;j=temp;，结果为0