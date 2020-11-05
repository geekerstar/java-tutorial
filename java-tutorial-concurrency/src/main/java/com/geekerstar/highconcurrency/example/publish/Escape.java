package com.geekerstar.highconcurrency.example.publish;

import com.geekerstar.highconcurrency.annoations.NotRecommend;
import com.geekerstar.highconcurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author geekerstar
 * date: 2019/1/22 09:01
 * description: 对象逸出
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape () {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
