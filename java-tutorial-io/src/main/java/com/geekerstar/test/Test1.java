package com.geekerstar.test;

import org.junit.Test;

import java.io.File;

/**
 * @author geekerstar
 * @date 2021/8/4 17:12
 * @description
 */
public class Test1 {

    @Test
    public void test1(){
        File f = new File("com/geekerstar/io/file/Test1.java");
        // 返回此File的绝对路径名字符串。
        System.out.println("文件绝对路径:"+f.getAbsolutePath());
        // 将此File转换为路径名字符串。
        System.out.println("文件构造路径:"+f.getPath());
        // 返回由此File表示的文件或目录的名称。
        System.out.println("文件名称:"+f.getName());
        // 返回由此File表示的文件的长度。
        System.out.println("文件长度:"+f.length()+"字节");

        File f2 = new File("com/geekerstar/io/file");
        System.out.println("目录绝对路径:"+f2.getAbsolutePath());
        System.out.println("目录构造路径:"+f2.getPath());
        System.out.println("目录名称:"+f2.getName());
        System.out.println("目录长度:"+f2.length());

        File f3 = new File("d:\\aaa\\bbb.java");
        File f4 = new File("d:\\aaa");
        // 判断是否存在
        System.out.println("d:\\aaa\\bbb.java 是否存在:"+f3.exists());
        System.out.println("d:\\aaa 是否存在:"+f4.exists());
        // 判断是文件还是目录
        System.out.println("d:\\aaa 文件?:"+f4.isFile());
        System.out.println("d:\\aaa 目录?:"+f4.isDirectory());
    }

    @Test
    public void test2(){
        File dir = new File("/Users/geekerstar/work/code/java/local/java-tutorial/java-tutorial-skill/src/main/java/com/geekerstar");
        //获取当前目录下的文件以及文件夹的名称。
        String[] names = dir.list();
        for(String name : names){
            System.out.println(name);
        }
        //获取当前目录下的文件以及文件夹对象，只要拿到了文件对象，那么就可以获取更多信息
        // 调用listFiles方法的File对象，表示的必须是实际存在的目录，否则返回null，无法进行遍历。
        File[] files = dir.listFiles();
        for (File file : files) {
            System.out.println(file);
        }
    }
}
