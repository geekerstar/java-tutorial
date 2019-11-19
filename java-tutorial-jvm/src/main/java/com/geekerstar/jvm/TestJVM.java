package com.geekerstar.jvm;

/**
 * @author geekerstar
 * @date 2018/12/25
 * description
 */
public class TestJVM {
    public static void main(String[] args) {
        String str = System.getProperty("str");
        if(str == null){
            System.out.println("geekerstar");
        }else{
            System.out.println(str);
        }
    }
}
