package com.geekerstar.effective;

import java.io.BufferedReader;
import java.io.FileReader;

public class Demo {

    public static void main(String[] args) {
       // 省略一些代码 （第 1 处）
        try {
            BufferedReader br = new BufferedReader(new FileReader("file.txt"));
            System.out.println(br.readLine());
            br.close();
        } catch (Exception e) {
            // 省略一些代码 （第 2 处）
//            System.exit(2);
        } finally {
            System.out.println("Exiting the program");
        }
    }
}
