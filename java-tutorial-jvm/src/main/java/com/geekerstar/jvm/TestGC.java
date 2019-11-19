package com.geekerstar.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class TestGC {

    /**
     * description: 实现：不断的产生新的数据（对象），随机的废弃对象（垃圾）
     *
     * auther: geekerstar
     * date: 2019/1/13 10:28
     * param: [args]
     * return: void
     */
    public static void main(String[] args) throws Exception {
        List<Object> list = new ArrayList<Object>();
        while (true){
            int sleep = new Random().nextInt(100);

            if(System.currentTimeMillis() % 2 == 0){
                // 当前的时间戳，是偶数
                list.clear();
            }else{
                // 向list中添加10000个对象
                for (int i = 0; i < 10000; i++) {
                    Properties properties = new Properties();

                    properties.put("key_" + i, "value_"+System.currentTimeMillis() + i);

                    list.add(properties);
                }
            }

            Thread.sleep(sleep);

        }

    }

}
