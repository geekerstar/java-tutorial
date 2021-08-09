package com.geekerstar.wx;

import org.junit.Test;

import java.util.*;

/**
 * @author geekerstar
 * @date 2021/8/9 14:36
 * @description https://blog.csdn.net/sihai12345/article/details/109465268
 */
public class TestMap {

    /**
     * 如何把一个Map转化为List
     */
    @Test
    public void test1() {
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "2");
        map.put(1, "1");
        map.put(3, "3");
        //把一个map的键转化为list
        List<Integer> keyList = new ArrayList<>(map.keySet());
        System.out.println(keyList);
        //把map的值转化为list
        List<String> valueList = new ArrayList<>(map.values());
        System.out.println(valueList);
        //把map的键值转化为list
        List entryList = new ArrayList(map.entrySet());
        System.out.println(entryList);
    }

    /**
     * 通过entrySet+for实现遍历
     */
    @Test
    public void test2(){
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "2");
        map.put(1, "1");
        map.put(3, "3");
        for(Map.Entry entry: map.entrySet()) {
            // get key
            Integer key = (Integer) entry.getKey();
            // get value
            String value = (String) entry.getValue();
            System.out.println("key:"+key+",value:"+value);
        }
    }

    /**
     * 通过Iterator+while实现遍历
     */
    @Test
    public void test3(){
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "2");
        map.put(1, "1");
        map.put(3, "3");
        Iterator itr = map.entrySet().iterator();
        while(itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            // get key
            Integer key = (Integer) entry.getKey();
            // get value
            String value = (String) entry.getValue();
            System.out.println("key:"+key+",value:"+value);
        }
    }

    /**
     * 如何根据Map的keys进行排序
     * 把Map.Entry放进list，再用Comparator对list进行排序
     */
    @Test
    public void test4(){
        Map<String, String> map = new HashMap<>();
        map.put("2010", "2010");
        map.put("1999", "1999");
        map.put("3010", "3010");
        List<Map.Entry<String,String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (Map.Entry e1, Map.Entry e2)-> {
            return e1.getKey().toString().compareTo(e2.getKey().toString());
        });
        for (Map.Entry entry : list) {
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
        }
    }

    /**
     * 使用SortedMap+TreeMap+Comparator实现
     */
    @Test
    public void test5(){
        Map<String, String> map = new HashMap<>();
        map.put("2010", "2010");
        map.put("1999", "1999");
        map.put("3010", "3010");
        SortedMap sortedMap = new TreeMap(new Comparator<String>() {
            @Override
            public int compare(String k1, String k2) {
                return k1.compareTo(k2);
            }
        });
        sortedMap.putAll(map);
        Iterator itr = sortedMap.entrySet().iterator();
        while(itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            // get key
            String key = (String) entry.getKey();
            // get value
            String value = (String) entry.getValue();
            System.out.println("key:"+key+",value:"+value);
        }
    }

    /**
     * 如何对Map的values进行排序
     */
    @Test
    public void test6(){
        Map<String, String> map = new HashMap<>();
        map.put("2010", "2010");
        map.put("1999", "1999");
        map.put("3010", "3010");
        List <Map.Entry<String,String>>list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (Map.Entry e1, Map.Entry e2)-> {
                    return e1.getValue().toString().compareTo(e2.getValue().toString());
                }
        );
        for (Map.Entry entry : list) {
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
        }
    }

    /**
     * 如何初始化一个静态/不可变的Map
     */
    private static final Map <Integer,String>map;
    static {
        map = new HashMap<Integer, String>();
        map.put(1, "one");
        map.put(2, "two");
    }

    @Test
    public void test7(){
        map.put(3, "three");
        Iterator itr = map.entrySet().iterator();
        while(itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            // get key
            Integer key = (Integer) entry.getKey();
            // get value
            String value = (String) entry.getValue();
            System.out.println("key:"+key+",value:"+value);
        }
    }

    /**
     * 真正实现一个静态不可变的map，需要Collections.unmodifiableMap
     */
    private static final Map<Integer, String> map1;
    static {
        Map<Integer,String> aMap = new HashMap<>();
        aMap.put(1, "one");
        aMap.put(2, "two");
        map1 = Collections.unmodifiableMap(aMap);
    }

    @Test
    public void test8(){
        map1.put(3, "3");
        Iterator itr = map1.entrySet().iterator();
        while(itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            // get key
            Integer key = (Integer) entry.getKey();
            // get value
            String value = (String) entry.getValue();
            System.out.println("key:"+key+",value:"+value);
        }

    }
}
