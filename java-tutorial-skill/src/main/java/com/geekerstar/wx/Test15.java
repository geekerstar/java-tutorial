package com.geekerstar.wx;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author geekerstar
 * @date 2021/8/9 11:34
 * @description https://mp.weixin.qq.com/s?__biz=MzAxNjk4ODE4OQ==&mid=2247487221&idx=3&sn=ac16de275ccb4c74f7031dc11d9e5c40&chksm=9bed2f87ac9aa691565e3756f4772ee21c38f356d81f17a20962434011da247270b5dff16f04&mpshare=1&scene=1&srcid=&sharer_sharetime=1572396925548&sharer_shareid=535c00d0d7095600f2fcdf96cc5a31ba#rd
 *
 * 递归中使用computeIfAbsent会造成CPU100%
 */
public class Test15 {

    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();
        map.computeIfAbsent("geek",key -> map.computeIfAbsent("dd",key2->"value"));
    }
}
