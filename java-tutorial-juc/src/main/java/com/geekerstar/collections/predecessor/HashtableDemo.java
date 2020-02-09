package com.geekerstar.collections.predecessor;

import java.util.Hashtable;

/**
 * @author geekerstar
 * @date 2020/2/9 14:24
 * @description
 */
public class HashtableDemo {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("学完以后跳槽涨薪幅度", "80%");
        System.out.println(hashtable.get("学完以后跳槽涨薪幅度"));
    }
}
