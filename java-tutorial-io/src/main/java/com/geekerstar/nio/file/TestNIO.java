package com.geekerstar.nio.file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

//通过NIO实现文件IO
public class TestNIO {

    @Test  //往本地文件中写数据
    public void  test1() throws  Exception{
        //1. 创建输出流
        FileOutputStream fos=new FileOutputStream("basic.txt");
        //2. 从流中得到一个通道
        FileChannel fc=fos.getChannel();
        //3. 提供一个缓冲区
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        //4. 往缓冲区中存入数据
        String str="hello,nio";
        buffer.put(str.getBytes());
        //5. 翻转缓冲区
        buffer.flip();
        //6. 把缓冲区写到通道中
        fc.write(buffer);
        //7. 关闭
        fos.close();
    }

    @Test  //从本地文件中读取数据
    public void test2() throws  Exception{
        File file=new File("basic.txt");
        //1. 创建输入流
        FileInputStream fis=new FileInputStream(file);
        //2. 得到一个通道
        FileChannel fc=fis.getChannel();
        //3. 准备一个缓冲区
        ByteBuffer buffer=ByteBuffer.allocate((int)file.length());
        //4. 从通道里读取数据并存到缓冲区中
        fc.read(buffer);
        System.out.println(new String(buffer.array()));
        //5. 关闭
        fis.close();
    }

    @Test  //使用NIO实现文件复制
    public void test3() throws  Exception{
        //1. 创建两个流
        FileInputStream fis=new FileInputStream("basic.txt");
        FileOutputStream fos=new FileOutputStream("c:\\test\\basic.txt");

        //2. 得到两个通道
        FileChannel sourceFC=fis.getChannel();
        FileChannel destFC=fos.getChannel();

        //3. 复制
        destFC.transferFrom(sourceFC,0,sourceFC.size());

        //4. 关闭
        fis.close();
        fos.close();
    }

}
