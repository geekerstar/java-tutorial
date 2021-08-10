package com.geekerstar.wx;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2021/8/10 10:29
 * @description
 *
 * https://blog.csdn.net/tangtong1/article/details/88958299
 *
 * https://mp.weixin.qq.com/s?__biz=MzIwMTY0NDU3Nw==&mid=2651940838&idx=2&sn=bca63e618360ff94a8b5597b0930ed72&chksm=8d0f08a8ba7881be3d7085021906bad88981e9ee0f5d272359f784a9ab167638f14d18bf9550&mpshare=1&scene=1&srcid=&sharer_sharetime=1574570483894&sharer_shareid=535c00d0d7095600f2fcdf96cc5a31ba#rd
 */
public class TestLinkedHashMap {

    @Test
    public void test2(){
        //accessOrder默认为false
        Map<String, String> accessOrderFalse = new LinkedHashMap<>();
        accessOrderFalse.put("1","1");
        accessOrderFalse.put("2","2");
        accessOrderFalse.put("3","3");
        accessOrderFalse.put("4","4");
        System.out.println("acessOrderFalse："+accessOrderFalse.toString());

        //accessOrder设置为true
        Map<String, String> accessOrderTrue = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderTrue.put("1","1");
        accessOrderTrue.put("2","2");
        accessOrderTrue.put("3","3");
        accessOrderTrue.put("4","4");
        accessOrderTrue.get("2");//获取键2
        accessOrderTrue.get("3");//获取键3
        System.out.println("accessOrderTrue："+accessOrderTrue.toString());
    }

    @Test
    public void test1(){
        // 创建一个只有5个元素的缓存
        LRU<Integer, Integer> lru = new LRU<>(5, 0.75f);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(4, 4);
        lru.put(5, 5);
        lru.put(6, 6);
        lru.put(7, 7);

        System.out.println(lru.get(4));

        lru.put(6, 666);

        // 输出: {3=3, 5=5, 7=7, 4=4, 6=666}
        // 可以看到最旧的元素被删除了
        // 且最近访问的4被移到了后面
        System.out.println(lru);
    }
}

class LRU<K, V> extends LinkedHashMap<K, V> {

    // 保存缓存的容量
    private int capacity;

    public LRU(int capacity, float loadFactor) {
        super(capacity, loadFactor, true);
        this.capacity = capacity;
    }

    /**
     * 重写removeEldestEntry()方法设置何时移除旧元素
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当元素个数大于了缓存的容量, 就移除元素
        return size() > this.capacity;
    }
}
