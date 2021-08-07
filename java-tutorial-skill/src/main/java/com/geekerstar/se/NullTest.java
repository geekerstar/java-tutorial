package com.geekerstar.se;

import java.util.HashMap;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2021/7/23 13:31
 * @description https://mp.weixin.qq.com/s/iFiTHBWVDUpEhP8-IZzX2g
 */
public class NullTest {

    public static void main(String[] args) {
        Map<String,Boolean> map =  new HashMap<String, Boolean>();

        Boolean b = (map!=null ? map.get("test") : false);
        Boolean c = (map!=null ? map.get("test") : Boolean.FALSE);


    }
}
