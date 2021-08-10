package com.geekerstar.wx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author geekerstar
 * @date 2021/8/10 15:53
 * @description
 *
 * https://mp.weixin.qq.com/s?__biz=MzA3ODQ0Mzg2OA==&mid=2649051062&idx=3&sn=85b856780c75629a11de6e27b88a72f9&chksm=87534b85b024c293d9518990cb30c037a723d78b572d2fa2f53434ed1daee5ddcc9fde876a61&mpshare=1&scene=1&srcid=&sharer_sharetime=1577424007132&sharer_shareid=535c00d0d7095600f2fcdf96cc5a31ba#rd
 */
public class TestArrayList {

    @Test
    public void test1(){
        List<String> arrayList1 = new ArrayList<String>();
        arrayList1.add("1");
        arrayList1.add("2");
        for (String s : arrayList1) {
            if("1".equals(s)){
                arrayList1.remove(s);
            }
        }
    }

    @Test
    public void test2(){
        List<String> arrayList2 = new ArrayList<String>();
        arrayList2.add("2");
        arrayList2.add("1");
        for (String s : arrayList2) {
            if("1".equals(s)){
                arrayList2.remove(s);
            }
        }
    }

    public void test3(){
        List<String> arrayList2 = new ArrayList<String>();
        arrayList2.add("2");
        arrayList2.add("1");
        Iterator<String> iterator = arrayList2.iterator();
        while(iterator.hasNext()){
            String item = iterator.next();
            if("1".equals(item)){
                iterator.remove();
            }
        }
    }

    @Test
    public void arraylistDemo(){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        System.out.printf("Before add:arrayList.size() = %d\n",arrayList.size());

        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(5);
        arrayList.add(7);
        arrayList.add(9);
        System.out.printf("After add:arrayList.size() = %d\n",arrayList.size());

        System.out.println("Printing elements of arrayList");
        // 三种遍历方式打印元素
        // 第一种：通过迭代器遍历
        System.out.print("通过迭代器遍历:");
        Iterator<Integer> it = arrayList.iterator();
        while(it.hasNext()){
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 第二种：通过索引值遍历
        System.out.print("通过索引值遍历:");
        for(int i = 0; i < arrayList.size(); i++){
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();

        // 第三种：for循环遍历
        System.out.print("for循环遍历:");
        for(Integer number : arrayList){
            System.out.print(number + " ");
        }

        // toArray用法
        // 第一种方式(最常用)
        Integer[] integer = arrayList.toArray(new Integer[0]);

        // 第二种方式(容易理解)
        Integer[] integer1 = new Integer[arrayList.size()];
        arrayList.toArray(integer1);

        // 抛出异常，java不支持向下转型
        //Integer[] integer2 = new Integer[arrayList.size()];
        //integer2 = arrayList.toArray();
        System.out.println();

        // 在指定位置添加元素
        arrayList.add(2,2);
        // 删除指定位置上的元素
        arrayList.remove(2);
        // 删除指定元素
        arrayList.remove((Object)3);
        // 判断arrayList是否包含5
        System.out.println("ArrayList contains 5 is: " + arrayList.contains(5));

        // 清空ArrayList
        arrayList.clear();
        // 判断ArrayList是否为空
        System.out.println("ArrayList is empty: " + arrayList.isEmpty());
    }

    @Test
    public void test4(){
        String[] arrays = {"1","2","3"};
        List<String> list = new ArrayList<>(Arrays.asList(arrays));
        list.removeIf("1"::equals);
        System.out.println(list);
    }
}
