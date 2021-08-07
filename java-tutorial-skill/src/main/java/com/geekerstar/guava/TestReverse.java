package com.geekerstar.guava;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geekerstar
 * @date 2020/12/13 11:38
 * @description
 */
public class TestReverse {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>() {{
            add("10");
            add("20");
            add("30");
            add("40");
        }};
        System.out.println("反转之前" + JSONUtil.toJsonStr(list));
        list = Lists.reverse(list);
        System.out.println("反转之后" + JSONUtil.toJsonStr(list));
    }
}
