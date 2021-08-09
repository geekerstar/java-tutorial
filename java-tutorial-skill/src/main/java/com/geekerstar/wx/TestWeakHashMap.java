package com.geekerstar.wx;

import org.junit.Test;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author geekerstar
 * @date 2021/8/9 19:59
 * @description
 *
 * https://mp.weixin.qq.com/s?__biz=MzkzODE3OTI0Ng==&mid=2247491172&idx=1&sn=7e4028ccd0c3e019ffffa8b5cedcb9ca&source=41#wechat_redirect
 */
public class TestWeakHashMap {

    @Test
    public void test() {
        Map weakHashMap = new WeakHashMap();
        //向weakHashMap中添加4个元素
        for (int i = 0; i < 3; i++) {
            weakHashMap.put("key-" + i, "value-" + i);
        }
        //输出添加的元素
        System.out.println("数组长度：" + weakHashMap.size() + "，输出结果：" + weakHashMap);
        //主动触发一次GC
        System.gc();
        //再输出添加的元素
        System.out.println("数组长度：" + weakHashMap.size() + "，输出结果：" + weakHashMap);
    }
}
