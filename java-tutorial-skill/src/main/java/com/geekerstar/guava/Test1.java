package com.geekerstar.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @author geekerstar
 * @date 2021/7/29 17:14
 * @description
 */
public class Test1 {

    /**
     * Guava 去掉空格的分割方法
     */
    @Test
    public void test1(){
        String a =",a, ,  b  c ,";
        // Splitter 是 Guava 提供的 API
        List<String> list = Splitter.on(',')
                .trimResults()// 去掉空格
                .omitEmptyStrings()// 去掉空值
                .splitToList(a);
        System.out.println(list);
    }

    /**
     * 依次 join 多个字符串，Joiner 是 Guava 提供的 API
     */
    @Test
    public void test2(){
        Joiner joiner = Joiner.on(",").skipNulls();
        String result = joiner.join("hello",null,"china");
        System.out.println("依次 join 多个字符串:");
        System.out.println(result);

        List<String> list = Lists.newArrayList(new String[]{"hello","china",null});
        System.out.println("自动删除 list 中空值:");
        System.out.println(joiner.join(list));
    }



}
