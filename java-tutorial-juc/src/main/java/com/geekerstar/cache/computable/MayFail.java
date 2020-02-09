package com.geekerstar.cache.computable;

import java.io.IOException;

/**
 * @author geekerstar
 * @date 2020/2/9 19:25
 * @description 耗时计算的实现类，有概率计算失败
 */
public class MayFail implements Computable<String, Integer> {

    @Override
    public Integer compute(String arg) throws Exception {
        double random = Math.random();
        if (random > 0.5) {
            throw new IOException("读取文件出错");
        }
        Thread.sleep(3000);
        return Integer.valueOf(arg);
    }
}
