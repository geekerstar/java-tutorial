package com.geekerstar.wx;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @author geekerstar
 * @date 2021/7/23 17:05
 * @description
 *
 * https://mp.weixin.qq.com/s?__biz=MzU2MTI4MjI0MQ==&mid=2247486483&idx=1&sn=fb17ae7b0e6262d38e928184c423ccdd&chksm=fc7a61bdcb0de8ab4c805dd615a030a2869e9f8d7bd3fc46e18ff55eb30183d2bf5dfb9b06de&mpshare=1&scene=1&srcid=#rd
 */
public class Test1 {

    @Test
    public void test5() {
        // for循环内使用try catch
        long start = System.currentTimeMillis();
        int a = 0;
        for(int i=0;i<1000000000;i++){
            try {
                a++;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        long useTime = System.currentTimeMillis()-start;
        System.out.println("useTime:"+useTime);
    }

    @Test
    public void test6(){
        // for循环外使用try catch
        long start = System.currentTimeMillis();
        int a = 0;
        try {
            for (int i=0;i<1000000000;i++){
                a++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        long useTime = System.currentTimeMillis()-start;
        System.out.println(useTime);
    }

    /**
     * 调用方法时传递的参数以及在调用中创建的临时变量都保存在栈（Stack）中，速度快。其他变量，如静态变量、实例变量等，都在堆（Heap）中创建，速度较慢。
     */
    @Test
    public void test7() {
        // 使用局部变量进行计算
        long start = System.currentTimeMillis();
        int a = 0;
        for(int i=0;i<1000000000;i++){
            a++;
        }
        long useTime = System.currentTimeMillis()-start;
        System.out.println("useTime:"+useTime);
    }

    static int aa = 0;
    @Test
    public void test8(){
        // 将局部变量替换为类的静态变量
        long start = System.currentTimeMillis();
        for (int i=0;i<1000000000;i++){
            aa++;
        }
        long useTime = System.currentTimeMillis()-start;
        System.out.println("useTime:"+useTime);
    }

    @Test
    public void test9() {
        // 使用算术运算
        long start = System.currentTimeMillis();
        int a = 0;
        for(int i=0;i<1000000000;i++){
            a*=2;
            a/=2;
        }
        long useTime = System.currentTimeMillis()-start;
        System.out.println("useTime:"+useTime);
    }

    @Test
    public void test10(){
        // 位运算
        long start = System.currentTimeMillis();
        int aa = 0;
        for (int i=0;i<1000000000;i++){
            aa<<=1;
            aa>>=1;
        }
        long useTime = System.currentTimeMillis()-start;
        System.out.println("useTime:"+useTime);
    }


    @Test
    public void testArrayCopy(){
        // 数组复制
        int size = 100000;
        int[] array = new int[size];
        int[] arraydest = new int[size];

        for(int i=0;i<array.length;i++){
            array[i] = i;
        }
        long start = System.currentTimeMillis();
        for (int k=0;k<1000;k++){
            //进行复制
            System.arraycopy(array,0,arraydest,0,size);
        }
        long useTime = System.currentTimeMillis()-start;
        System.out.println("useTime:"+useTime);
    }

    @Test
    public void testArrayCopy99(){
        // 数组复制，自己实现
        int size = 100000;
        int[] array = new int[size];
        int[] arraydest = new int[size];

        for(int i=0;i<array.length;i++){
            array[i] = i;
        }
        long start = System.currentTimeMillis();
        for (int k=0;k<1000;k++){
            for(int i=0;i<size;i++){
                arraydest[i] = array[i];
            }
        }
        long useTime = System.currentTimeMillis()-start;
        System.out.println("useTime:"+useTime);
    }

    @Test
    public void testOutAndInputStream(){
        // 直接使用InputStream和OutputStream进行文件读写
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt"));
            long start = System.currentTimeMillis();
            for(int i=0;i<10000;i++){
                dataOutputStream.writeBytes(Objects.toString(i)+"\r\n");
            }
            dataOutputStream.close();
            long useTime = System.currentTimeMillis()-start;
            System.out.println("写入数据--useTime:"+useTime);
            //开始读取数据
            long startInput = System.currentTimeMillis();
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt"));

            while (dataInputStream.readLine() != null){
            }
            dataInputStream.close();
            long useTimeInput = System.currentTimeMillis()-startInput;
            System.out.println("读取数据--useTimeInput:"+useTimeInput);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testBufferedStream(){
        // 使用缓冲
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt")));
            long start = System.currentTimeMillis();
            for(int i=0;i<10000;i++){
                dataOutputStream.writeBytes(Objects.toString(i)+"\r\n");
            }
            dataOutputStream.close();
            long useTime = System.currentTimeMillis()-start;
            System.out.println("写入数据--useTime:"+useTime);
            //开始读取数据
            long startInput = System.currentTimeMillis();
            DataInputStream dataInputStream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt")));

            while (dataInputStream.readLine() != null){
            }
            dataInputStream.close();
            long useTimeInput = System.currentTimeMillis()-startInput;
            System.out.println("读取数据--useTimeInput:"+useTimeInput);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Test
    public void testWriterAndReader(){

        try {
            long start = System.currentTimeMillis();
            FileWriter fileWriter = new FileWriter("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt");
            for (int i=0;i<100000;i++){
                fileWriter.write(Objects.toString(i)+"\r\n");
            }
            fileWriter.close();
            long useTime = System.currentTimeMillis()-start;
            System.out.println("写入数据--useTime:"+useTime);
            //开始读取数据
            long startReader = System.currentTimeMillis();
            FileReader fileReader = new FileReader("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt");
            while (fileReader.read() != -1){
            }
            fileReader.close();
            long useTimeInput = System.currentTimeMillis()-startReader;
            System.out.println("读取数据--useTimeInput:"+useTimeInput);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testBufferedWriterAndReader(){

        try {
            long start = System.currentTimeMillis();
            BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt"));
            for (int i=0;i<100000;i++){
                fileWriter.write(Objects.toString(i)+"\r\n");
            }
            fileWriter.close();
            long useTime = System.currentTimeMillis()-start;
            System.out.println("写入数据--useTime:"+useTime);
            //开始读取数据
            long startReader = System.currentTimeMillis();
            BufferedReader fileReader = new BufferedReader(
                    new FileReader("/IdeaProjects/client2/src/test/java/com/client2/cnblogtest/teststream.txt"));
            while (fileReader.read() != -1){
            }
            fileReader.close();
            long useTimeInput = System.currentTimeMillis()-startReader;
            System.out.println("读取数据--useTimeInput:"+useTimeInput);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
