package com.geekerstar.guava;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author geekerstar
 * @date 2020/12/13 11:42
 * @description
 */
public class TestMapsDifference {
    public static void main(String[] args) {
        // ImmutableMap.of 也是 Guava 提供初始化 Map 的方法，入参格式为 k1,v1,k2,v2,k3,v3……
        Map<String, String> leftMap = ImmutableMap.of("1", "1", "2", "2", "3", "3");
        Map<String, String> rightMap = ImmutableMap.of("2", "2", "3", "30", "4", "4");
        MapDifference difference = Maps.difference(leftMap, rightMap);
        System.out.println("左边 map 独有 key："+difference.entriesOnlyOnLeft());
        System.out.println("右边 map 独有 key："+difference.entriesOnlyOnRight());
        System.out.println("左右边 map 都有 key，并且 value 相等："+difference.entriesInCommon());
        System.out.println("左右边 map 都有 key，但 value 不等："+difference.entriesDiffering());
    }
}
