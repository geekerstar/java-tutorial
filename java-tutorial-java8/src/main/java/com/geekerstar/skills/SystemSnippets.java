package com.geekerstar.skills;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * @author geekerstar
 * @date 2020/4/7 10:47
 * @description
 */
public class SystemSnippets {

    /**
     * 以小写字符串的形式获取操作系统的名称。
     *
     * @return
     */
    public static String osName() {
        return System.getProperty("os.name").toLowerCase();
    }

    /**
     * 检查JVM是否为debug模式。
     * @return
     */
    public static boolean isDebuggerAttached() {
        final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return runtimeMXBean.getInputArguments()
                .stream()
                .anyMatch(arg -> arg.contains("-agentlib:jdwp"));

    }
}
