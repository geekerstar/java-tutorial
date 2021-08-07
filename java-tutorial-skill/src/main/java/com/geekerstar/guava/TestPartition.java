package com.geekerstar.guava;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geekerstar
 * @date 2020/12/13 11:41
 * @description
 */
public class TestPartition {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>() {{
            add("10");
            add("20");
            add("30");
            add("40");
        }};
        System.out.println("分组之前" + JSONUtil.toJsonStr(list));
        List<List<String>> list2 = Lists.partition(list, 3);
        System.out.println("分组之后" + JSONUtil.toJsonStr(list2));

    }
}
