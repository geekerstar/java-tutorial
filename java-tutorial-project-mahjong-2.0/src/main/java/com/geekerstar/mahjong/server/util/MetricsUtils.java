package com.geekerstar.mahjong.server.util;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocatorMetric;
import io.netty.util.internal.PlatformDependent;

import java.util.concurrent.TimeUnit;

public class MetricsUtils {

    public static void start() {
        // 注册
        MetricRegistry metricRegistry = new MetricRegistry();
        metricRegistry.register("usedDirectMemory"
                , (Gauge<Long>) () -> PlatformDependent.usedDirectMemory());
        metricRegistry.register("maxDirectMemory"
                , (Gauge<Long>) () -> PlatformDependent.maxDirectMemory());

        // 打印到控制台
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).build();
        consoleReporter.start(1, TimeUnit.SECONDS);
    }
}
