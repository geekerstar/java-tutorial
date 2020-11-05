package com.geekerstar.highconcurrency.example.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author geekerstar
 * date: 2019/1/24 11:33
 * description:
 */
@Slf4j
public class RateLimiterExample2 {

    private static RateLimiter rateLimiter = RateLimiter.create(5);

    public static void main(String[] args) throws Exception {

        for (int index = 0; index < 100; index++) {
            rateLimiter.acquire();
            handle(index);
        }
    }

    private static void handle(int i) {
        log.info("{}", i);
    }
}

