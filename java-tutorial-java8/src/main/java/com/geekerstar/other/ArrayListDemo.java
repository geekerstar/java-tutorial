package com.geekerstar.other;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author geekerstar
 * date: 2019/2/17 10:27
 * description:
 */
public class ArrayListDemo {

    @Test
    public void testArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        System.out.println("Before add:arrayList.size() = "+ arrayList.size());

        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(5);
        arrayList.add(7);
        arrayList.add(9);
        System.out.println("After add:arrayList.size() = "+ arrayList.size());

        System.out.println("Printing elements of arrayList");
        //三种遍历方式打印元素
        // 第一种：通过迭代器遍历
        System.out.println("通过迭代器遍历");
        Iterator<Integer> it = arrayList.iterator();
        while(it.hasNext()){
            System.out.println(it.next()+" ");

        }
        System.out.println();

        //第二种：通过索引遍历
        System.out.println("通过索引遍历");
        for (int i = 0;i < arrayList.size(); i++){
            System.out.println(arrayList.get(i)+" ");

        }
        System.out.println();

        //第三种：for循环遍历
        System.out.println("for循环遍历");
        for(Integer number : arrayList){
            System.out.println(number+" ");

        }

        //toArray用法
        //第一种方式（最常用）
        Integer[] integers = arrayList.toArray(new Integer[0]);

        //第二种方式（容易理解）
        Integer[] integers1 = new Integer[arrayList.size()];
        arrayList.toArray(integers1);

        //抛出异常，Java不支持向下转型
//        Integer[] integers2 = new Integer[arrayList.size()];
//        integers2 = arrayList.toArray();
        System.out.println();

        //在指定位置添加元素
        arrayList.add(2,2);
        //删除指定位置上的元素
        arrayList.remove(2);
        //删除指定元素
        arrayList.remove((Object)3);
        //判断ArrayList是否包含5
        System.out.println("ArrayList contains 5 is: "+arrayList.contains(5));
        //清空ArrayList
        arrayList.clear();
        //判断ArrayList是否为空
        System.out.println("ArrayList is empty: "+arrayList.isEmpty());


    }
}
