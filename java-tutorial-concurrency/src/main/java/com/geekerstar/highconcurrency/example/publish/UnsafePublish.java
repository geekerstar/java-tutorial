package com.geekerstar.highconcurrency.example.publish;

import com.geekerstar.highconcurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author geekerstar
 * date: 2019/1/22 08:59
 * description:
 *
 * 发布对象
 * 发布对象：使一个对象能够被当前范围之外的的代码所使用
 * 对象逸出：一种错误的发布。当一个对象还没有构造完成时，就使它被其他线程所见
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
