package com.geekerstar.waiting.algorithm;

import java.util.*;

/**
 * @author geekerstar
 * date: 2019-08-03 08:46
 * description:
 *
 * 已知一个 HashMap<Integer，User>集合， User 有 name（String）和 age（int）属性。请写一个方法实现对 HashMap 的排序功能，该方法接收 HashMap<Integer，User>为形参，返回类型为 HashMap<Integer，User>， 要求对 HashMap 中的 User 的 age 倒序进行排序。排序时 key=value 键值对不得拆散。
 *
 */
public class HashSort {
    public static void main(String[] args) {
        HashMap<Integer,User> users = new HashMap<Integer, User>();
        users.put(1,new User("张三",25));
        users.put(2,new User("李四",22));
        users.put(3,new User("王五",28));
        System.out.println(users);
        HashMap<Integer,User> sortHashMap = sortHashMap(users);
        System.out.println(sortHashMap);

    }

    private static HashMap<Integer, User> sortHashMap(HashMap<Integer, User> map) {
        // 首先拿到map的键值对集合
        Set<Map.Entry<Integer,User>> entrySet = map.entrySet();

        // 将set集合转为List集合，为了使用工具类的排序方法
        List<Map.Entry<Integer,User>> list = new ArrayList<>(entrySet);

        // 使用Collections集合工具类对list进行排序，排序规则使用匿名内部类来实现
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                // 按照要求根据User的age倒序排列
                return o2.getValue().getAge() - o1.getValue().getAge();
            }
        });

        // 创建一个新的有序的HashMap子类的集合
        LinkedHashMap<Integer,User> linkedHashMap = new LinkedHashMap<>();

        // 将List中的数据存储在LinkedHashMap中
        for (Map.Entry<Integer,User> entry : list){
            linkedHashMap.put(entry.getKey(),entry.getValue());
        }

        return linkedHashMap;
    }
}
