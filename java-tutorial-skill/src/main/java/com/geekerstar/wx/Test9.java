package com.geekerstar.wx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author geekerstar
 * @date 2021/7/26 10:08
 * @description List去除重复数据
 *
 * https://mp.weixin.qq.com/s/EjaAO6bDq1wLWH2w4GZo8w
 *
 * https://mp.weixin.qq.com/s/VSOlMVyd0v6OkmSA2bBHOQ
 */
public class Test9 {

    /**
     * LinkedHashSet
     */
    @Test
    public void test1(){
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        System.out.println(numbersList);
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(numbersList);
        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(hashSet);
        System.out.println(listWithoutDuplicates);
    }

    /**
     * 使用 java8 新特性 stream 进行 List 去重
     */
    @Test
    public void test2(){
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        System.out.println(numbersList);
        List<Integer> listWithoutDuplicates = numbersList.stream().distinct().collect(Collectors.toList());
        System.out.println(listWithoutDuplicates);
    }

}
