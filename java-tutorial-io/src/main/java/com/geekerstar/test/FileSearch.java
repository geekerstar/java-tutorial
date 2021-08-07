package com.geekerstar.test;

import java.io.File;
import java.io.FileFilter;

/**
 * @author geekerstar
 * @date 2021/8/4 17:36
 * @description 搜索 D:\aaa 目录中的 .java 文件。
 * 分析：
 * 1. 目录搜索，无法判断多少级目录，所以使用递归，遍历所有目录。
 * 2. 遍历目录时，获取的子文件，通过文件名称，判断是否符合条件。
 */
public class FileSearch {

    public static void main(String[] args) {
        // 创建File对象
        File dir = new File("D:\\aaa");
        // 调用打印目录方法
        printDir1(dir);
    }

    public static void printDir1(File dir) {
        // 获取子文件和目录
        File[] files = dir.listFiles();
        // 循环打印
        for (File file : files) {
            if (file.isFile()) {
                // 是文件，判断文件名并输出文件绝对路径
                if (file.getName().endsWith(".java")) {
                    System.out.println("文件名:" + file.getAbsolutePath());
                }
            } else {
                // 是目录，继续遍历,形成递归
                printDir1(file);
            }
        }
    }

    /**
     * java.io.FileFilter 是一个接口，是File的过滤器。 该接口的对象可以传递给File类的 listFiles(FileFilter)作为参数， 接口中只有一个方法。
     * <p>
     * boolean accept(File pathname) ：测试pathname是否应该包含在当前File目录中，符合则返回true。
     * <p>
     * 分析：
     * 1. 接口作为参数，需要传递子类对象，重写其中方法。我们选择匿名内部类方式，比较简单。
     * <p>
     * 2. accept 方法，参数为File，表示当前File下所有的子文件和子目录。保留住则返回true，过滤掉则返回 false。保留规则：
     * 1. 要么是.java文件。
     * 2. 要么是目录，用于继续遍历。
     * <p>
     * 3. 通过过滤器的作用， listFiles(FileFilter) 返回的数组元素中，子文件对象都是符合条件的，可以直接打印。
     */
    public static void printDir2(File dir) {
        // 匿名内部类方式,创建过滤器子类对象
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".java") || pathname.isDirectory();
            }
        });
        // 循环打印
        for (File file : files) {
            if (file.isFile()) {
                System.out.println("文件名:" + file.getAbsolutePath());
            } else {
                printDir2(file);
            }
        }
    }

    public static void printDir3(File dir) {
        // lambda的改写
        File[] files = dir.listFiles(f -> f.getName().endsWith(".java") || f.isDirectory());
        // 循环打印
        for (File file : files) {
            if (file.isFile()) {
                System.out.println("文件名:" + file.getAbsolutePath());
            } else {
                printDir3(file);
            }
        }
    }
}
