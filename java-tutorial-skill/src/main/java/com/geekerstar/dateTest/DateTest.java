package com.geekerstar.dateTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author geekerstar
 * @date 2021/7/23 13:04
 * @description
 */
public class DateTest {

    public static void main(String[] args) {
        // date类型与LocalDateTime转换
        Date now = new Date();
        // Date-----> LocalDateTime 这里指定使用当前系统默认时区
        LocalDateTime localDateTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        // LocalDateTime------> Date 这里指定使用当前系统默认时区
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        // 按照 yyyy-MM-dd HH:mm:ss 转化时间
        LocalDateTime dateTime = LocalDateTime.parse("2020-05-07 22:34:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 将 LocalDateTime 格式化字符串
        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateTime);
    }
}
