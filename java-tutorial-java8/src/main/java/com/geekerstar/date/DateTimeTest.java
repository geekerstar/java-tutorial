package com.geekerstar.date;


import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

/**
 * @author geekerstar
 * date: 2019/11/11 14:12
 * description:
 * <p>
 * LocalDate是一个不可变类，在不考虑时区的情况下可以对日期（不包括时间）进行各种操作，它的默认格式是yyyy-MM-dd
 * <p>
 * LocalTime与LocalDate一样，也是一个不可变的类，默认格式是hh:mm:ss.zzz，它提供了对时间的各种操作
 * <p>
 * LocalDateTime是一个不可变的日期-时间对象，它既包含了日期同时又含有时间，默认格式是yyyy-MM-ddTHH-mm-ss.zzz
 * <p>
 * LocalDate、LocalTime、LocalDateTime、Instant为不可变对象，修改这些对象对象会返回一个副本
 */

public class DateTimeTest {


    @Test
    public void localDate() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        LocalDate localDate1 = LocalDate.of(2019, 1, 12);
        System.out.println(localDate1);

        int year = localDate.getYear();
        System.out.println(year);

        int i = localDate.get(ChronoField.YEAR);
        System.out.println(i);

        Month month = localDate.getMonth();
        System.out.println(month);

        int i1 = localDate.get(ChronoField.MONTH_OF_YEAR);
        System.out.println(i1);

        int dayOfMonth = localDate.getDayOfMonth();
        System.out.println(dayOfMonth);

        int i2 = localDate.get(ChronoField.DAY_OF_MONTH);
        System.out.println(i2);

        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        System.out.println(dayOfWeek);

        int i3 = localDate.get(ChronoField.DAY_OF_WEEK);
        System.out.println(i3);

    }

    @Test
    public void localTime() {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        LocalTime localTime1 = LocalTime.of(23, 23, 23);
        System.out.println(localTime1);

        int hour = localTime.getHour();
        System.out.println(hour);

        int i = localTime.get(ChronoField.HOUR_OF_DAY);
        System.out.println(i);

        int minute = localTime.getMinute();
        System.out.println(minute);

        int i1 = localTime.get(ChronoField.MINUTE_OF_HOUR);
        System.out.println(i1);

        int second = localTime.getSecond();
        System.out.println(second);

        int i2 = localTime.get(ChronoField.SECOND_OF_MINUTE);
        System.out.println(i2);
    }

    @Test
    public void localDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        LocalDateTime localDateTimeOf = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 56, 44);
        System.out.println(localDateTimeOf);

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(localDate, localTime);
        System.out.println(localDateTime1);

        LocalDateTime localDateTime2 = localDate.atTime(localTime);
        System.out.println(localDateTime2);

        LocalDateTime localDateTime3 = localTime.atDate(localDate);
        System.out.println(localDateTime3);

        LocalDate localDate1 = localDateTime.toLocalDate();
        System.out.println(localDate1);

        LocalTime localTime1 = localDateTime.toLocalTime();
        System.out.println(localTime1);

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        System.out.println(localDateTimeNow);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.println(zonedDateTime);
    }

    @Test
    public void instant() {
        Instant instant = Instant.now();

        // 获取秒数
        long epochSecond = instant.getEpochSecond();
        System.out.println(epochSecond);

        // 获取毫秒数
        long epochMilli = instant.toEpochMilli();
        System.out.println(epochMilli);

    }

    @Test
    public void modify() {
        LocalDateTime localDateTime = LocalDateTime.now();

        // 增加一年
        LocalDateTime plusYears = localDateTime.plusYears(1);
        System.out.println(plusYears);
        LocalDateTime plus = localDateTime.plus(1, ChronoUnit.YEARS);
        System.out.println(plus);

        // 减少一个月
        LocalDateTime minusMonths = localDateTime.minusMonths(1);
        System.out.println(minusMonths);
        LocalDateTime minus = localDateTime.minus(1, ChronoUnit.MONTHS);
        System.out.println(minus);

        // with修改某些值
        LocalDateTime withYear = localDateTime.withYear(2020);
        System.out.println(withYear);
        LocalDateTime with = localDateTime.with(ChronoField.YEAR, 2021);
        System.out.println(with);
    }

    @Test
    public void calcTime() {
        Set<String> set = ZoneId.getAvailableZoneIds();
        Set<String> treeSet = new TreeSet<String>() {
            {
                addAll(set);
            }
        };
        treeSet.stream().forEach(System.out::println);

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        Clock clock1 = Clock.systemUTC();
        System.out.println(clock1.instant());
        System.out.println(clock1.millis());

        // 时间计算
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.with(firstDayOfYear());
        System.out.println(localDate);

        // 格式化时间
        LocalDate localDate1 = LocalDate.of(2019, 10, 10);
        String s1 = localDate1.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = localDate1.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(s1);
        System.out.println(s2);

        // 自定义格式化(MM大写)
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s3 = localDate.format(dateTimeFormatter);
        System.out.println(s3);

        // 解析时间 和SimpleDateFormat相比，DateTimeFormatter是线程安全的
        LocalDate l1 = LocalDate.parse("20190101", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate l2 = LocalDate.parse("2019-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(l1);
        System.out.println(l2);

        LocalDate localDate11 = LocalDate.now();
        System.out.println("2年后日期：" + localDate11.plusYears(2));
        System.out.println("6月后日期：" + localDate11.plusMonths(6));
        System.out.println("3周后日期：" + localDate11.plusWeeks(3));
        System.out.println("15天后日期：" + localDate11.plusDays(15));
        System.out.println("2年前日期：" + localDate11.minusYears(2));
        System.out.println("6月前日期：" + localDate11.minusMonths(6));
        System.out.println("3周前日期：" + localDate11.minusWeeks(3));
        System.out.println("15天前日期：" + localDate11.minusDays(15));

        //获取某年份的第N天的日期
        LocalDate specialDay = LocalDate.ofYearDay(2018, 100);
        System.out.println("2018年的第100天：" + specialDay);

        //获取两个日期的间隔天数
        System.out.println(localDate.toEpochDay());
        long intervalDay = localDate.toEpochDay() - specialDay.toEpochDay();
        System.out.println("间隔天数： " + intervalDay);

        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);
        System.out.println(yearMonth.lengthOfMonth());
        System.out.println(yearMonth.isLeapYear());

        LocalDate localDate7 = LocalDate.now();
        LocalDate localDate8 = LocalDate.of(2020, 3, 16);
        Period period = Period.between(localDate7, localDate8);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    @Test
    public void compareDate() {
        LocalDate localDate = LocalDate.now();
        LocalDate otherDate = LocalDate.of(2018, 11, 11);
//equals方法用于比较两个日期是否相等
        if (localDate.equals(otherDate)) {
            System.out.println("localDate与otherDate相等！");
        } else {
            //isAfter和isBefore方法用于比较两个日期前后顺序
            if (localDate.isAfter(otherDate)) {
                System.out.println("localDate晚于otherDate！");
            }
            if (localDate.isBefore(otherDate)) {
                System.out.println("localDate早于otherDate！");
            }
        }

    }
}
