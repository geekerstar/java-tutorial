package com.geekerstar.jvm;

public class Test4 {

    public static void main(String[] args) {
        new Test4().m1();
        new Test4().m2();
    }

    public void m1(){
        String str = "";
        for (int i = 0; i < 5; i++) {
            str = str + i;
        }
        System.out.println(str);
    }

    public void m2(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(i);
        }
        System.out.println(sb.toString());
    }
}
