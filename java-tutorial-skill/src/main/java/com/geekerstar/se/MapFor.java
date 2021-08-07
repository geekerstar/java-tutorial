package com.geekerstar.se;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author geekerstar
 * @date 2021/7/23 09:50
 * @description
 */
public class MapFor {

    public static void main(String[] args) throws InterruptedException {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("a:", "1");
        hashMap.put("b", "2");
        hashMap.put("c", "3");

        // 不推荐
        // 得到keySet，遍历keySet得到所有的key
        Set<String> strings = hashMap.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {

            // HashMap的每个key
            String key = iterator.next();

            // 通过key可以获得对应的value，如果有看过HashMap的同学知道get方法的时间复杂度是O(1)
            System.out.println("key = " + key + ", value = " + hashMap.get(key));
        }

        //entrySet，可以直接拿到key和value，不用再使用get方法来得到value，所以比keySet更加推荐使用！
        // 得到entrySet，遍历entrySet得到结果
        Set<Map.Entry<String, String>> entrySet = hashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator1 = entrySet.iterator();
        while (iterator1.hasNext()) {
            Map.Entry<String, String> entry = iterator1.next();
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }


        // forEach用法
        hashMap.forEach((key, value) -> System.out.println("key = " + key + ", value = " + value));

    }

}
