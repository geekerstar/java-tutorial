package com.geekerstar.skills;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author geekerstar
 * @date 2020/4/7 10:46
 * @description
 */
public class ExceptionSnippets {

    /**
     * 将异常堆栈跟踪转换为字符串。
     *
     * @param throwable
     * @return
     */
    public static String stackTraceAsString(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
