package com.geekerstar.wx;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author geekerstar
 * @date 2021/7/25 18:06
 * @description String
 *
 * https://mp.weixin.qq.com/s?__biz=MzU0MzYxNjQ1Ng==&mid=2247483717&idx=1&sn=177354f12b5e5e60eb39fa4408485dcd&chksm=fb09fde2cc7e74f486ed4f98e2919bb178b810b4bf95522a8d6663ffbdf2b18a5d4413a33f62&mpshare=1&scene=1&srcid=1024Hv3gnavHy4vN6mlxekBs#rd
 *
 * https://blog.csdn.net/zhangjg_blog/article/details/18319521
 *
 * https://mp.weixin.qq.com/s/8R0VT1A9b0nw3Hbx9zDQ4A
 */
public class Test7 {

    @Test
    public void test1(){
        String stra = "hello2";
        String strb = "hello" + 2; //在编译期间已被优化成"hello2"
        System.out.println(stra == strb); //true

        String strc = "hello2";
        String strd = "hello";
        String stre = strd + 2; //字符串引用不会在编译期间被优化
        System.out.println(strc == stre); //false

        String strf = "hello2";
        final String strg = "hello"; //final修饰的变量编译时会在常量池保存一个副本
        String strh = strg + 2; //对final变量的访问在编译期间都会直接被替代为真实的值
        System.out.println(strf == strh); //true

        String stri = "hello2";
        final String strj = getHello(); //值是运行期间才确定的
        String strk = strj + 2;
        System.out.println(stri == strk); //false
    }

    public static String getHello() {
        return "hello";
    }

    @Test
    public void test2(){
        String string = "004-034556";
        String[] parts = string.split("-");
        String part1 = parts[0]; // 004
        String part2 = parts[1]; // 034556
        System.out.println(part1);
        System.out.println(part2);
    }

    @Test
    public void test3(){
        String ss = "123456";
        System.out.println("ss = " + ss);
        String replace = ss.replace('1', '0');
        System.out.println("ss = " + ss);
    }

    /**
     * 反射改变字符串
     * @throws Exception
     */
    @Test
    public void testReflection() throws Exception {
        //创建字符串"Hello World"， 并赋给引用s
        String s = "Hello World";
        System.out.println("s = " + s);	//Hello World
        //获取String类中的value字段
        Field valueFieldOfString = String.class.getDeclaredField("value");
        //改变value属性的访问权限
        valueFieldOfString.setAccessible(true);
        //获取s对象上的value属性的值
        char[] value = (char[]) valueFieldOfString.get(s);
        //改变value所引用的数组中的第5个字符
        value[5] = '_';
        System.out.println("s = " + s);  //Hello_World
    }

    /**
     * https://mp.weixin.qq.com/s?__biz=MzI3ODc3NzQ4NQ==&mid=2247485896&idx=1&sn=981512f319c107f5edef2a86320684aa&chksm=eb50922adc271b3c65c92df62cd6dca61a646b1c32e254ae5aec12980473e3bbd79ed805acce&mpshare=1&scene=1&srcid=&sharer_sharetime=1568782520565&sharer_shareid=535c00d0d7095600f2fcdf96cc5a31ba#rd
     */
    @Test
    public void test4(){
        String str = "ab";
        String str1 = (" a"+"b ").trim();
        String str2 = ("a"+"b").trim();
        System.out.println(str==str1);
        System.out.println(str==str2);
    }

    @Test
    public void test5(){
        int one = 123456789;
        double two = 123456.789;
        String s = String.format("第一个参数：%,d 第二个参数：%,.2f", one, two);
        System.out.println(s);
        String raw = "hello word";
        String str = String.format("|%-15s|", raw);
        System.out.println(str);
        int num = -1000;
        String str1 = String.format("%(,d", num);
        System.out.println(str1);

        double num1 = 123.456789;
        System.out.print(String.format("浮点类型：%.2f %n", num1));
        System.out.print(String.format("十六进制浮点类型：%a %n", num1));
        System.out.print(String.format("通用浮点类型：%g ", num1));

        Date date = new Date();
        System.out.printf("全部日期和时间信息：%tc%n",date);
        System.out.printf("年-月-日格式：%tF%n",date);
        System.out.printf("月/日/年格式：%tD%n",date);
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",date);
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n",date);
        System.out.printf("HH:MM格式（24时制）：%tR",date);
    }
}
