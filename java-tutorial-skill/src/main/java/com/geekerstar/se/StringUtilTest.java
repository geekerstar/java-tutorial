package com.geekerstar.se;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author geekerstar
 * @date 2021/7/23 12:47
 * @description
 *
 * https://mp.weixin.qq.com/s/LRT9Y-XYWhJXTj1KdBjiHA
 */
public class StringUtilTest {

    public static void main(String[] args) {
        // 字符串固定长度 8位，若不足，往左补 0    0000test
        System.out.println(StringUtils.leftPad("test", 8, "0"));
        // 默认替换所有关键字  zbz
        System.out.println(StringUtils.replace("aba", "a", "z"));
        // 替换关键字，仅替换一次  zba
        System.out.println(StringUtils.replaceOnce("aba", "a", "z"));
        // 使用正则表达式替换   ABC123
        System.out.println(StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", ""));


        //StringUtils 只能传入数组拼接字符串，不过我比较喜欢集合拼接，所以再推荐下 Guava 的 Joiner  test,1234,5678
        String[] array = new String[]{"test", "1234", "5678"};
        System.out.println(StringUtils.join(array, ","));

        List<String> list=new ArrayList<>();
        list.add("test");
        list.add("1234");
        list.add("5678");
        // 逗号分隔符，跳过 null
        Joiner joiner=Joiner.on(",").skipNulls();
        System.out.println(joiner.join(array));
        System.out.println(joiner.join(list));

        // 字符串拆分  [a, b, c]
        System.out.println(Arrays.toString(StringUtils.split("a..b.c", '.')));
        // 字符串拆分  [a, , b, c]
        System.out.println(Arrays.toString(StringUtils.splitByWholeSeparatorPreserveAllTokens("a..b.c", ".")));

        Splitter splitter = Splitter.on(",");
        // 返回是一个 List 集合，结果：[ab, , b, c]
        System.out.println(splitter.splitToList("ab,,b,c"));
        // 忽略空字符串，输出结果 [ab, b, c]
        System.out.println(splitter.omitEmptyStrings().splitToList("ab,,b,c"));
    }

}
