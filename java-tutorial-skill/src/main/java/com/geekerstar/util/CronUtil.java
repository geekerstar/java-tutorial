package com.geekerstar.util;

/**
 * @ClassName: CronUtil
 * @Description: Cron表达式工具类 目前支持三种常用的cron表达式 1.每天的某个时间点执行 例:12 12 12 * *
 *               ?表示每天12时12分12秒执行 2.每周的哪几天执行 例:12 12 12 ? * 1,2,3表示每周的周1周2周3
 *               ,12时12分12秒执行 3.每月的哪几天执行 例:12 12 12 1,21,13 * ?表示每月的1号21号13号
 *               12时12分12秒执行
 * @author
 * @date
 *
 */
public class CronUtil {

    /**
     *
     * 方法摘要：构建Cron表达式
     *
     * @param rate  频率 0秒；1分；2小时；3日；4月
     * @param cycle 周期
     * @return String
     */
    public static String createLoopCronExpression(int rate, int cycle) {
        String cron = "";
        switch (rate) {
        case 0:// 每cycle秒执行一次
            cron = "0/" + cycle + " * * * * ?";
            break;
        case 1:// 每cycle分钟执行一次
            cron = "0 0/" + cycle + " * * * ?";
            break;
        case 2:// 每cycle小时执行一次
            cron = "0 0 0/" + cycle + " * * ?";
            break;
        case 3:// 每cycle天的0点执行一次
            cron = "0 0 0 1/" + cycle + " * ?";
            break;
        case 4:// 每cycle月的1号0点执行一次
            cron = "0 0 0 1 1/" + cycle + " ? ";
            break;
        case 5://  每天cycle点执行一次
            cron = "0 0 " + cycle+ "  * * ?";
            break;
        default:// 默认每cycle秒执行一次
            cron = "0/1 * * * * ?";
            break;
        }
        return cron;
    }

    /**
     *
     * 方法摘要：构建Cron描述
     *
     * @param rate  频率 0秒；1分；2小时；3日；4月
     * @param cycle 周期
     * @return String
     */
    public static String createLoopCronDescription(int rate, int cycle) {
        String desc = "";
        switch (rate) {
        case 0:// 每cycle秒执行一次
            desc = "每隔" + cycle + "秒触发一次任务";
            break;
        case 1:// 每cycle分钟执行一次
            desc = "每隔" + cycle + "分钟触发一次任务";
            break;
        case 2:// 每cycle小时执行一次
            desc = "每隔" + cycle + "小时触发一次任务";
            break;
        case 3:// 每cycle天的0点执行一次
            desc = "每隔" + cycle + "天的0点触发一次任务";
            break;
        case 4:// 每cycle月的1号0点执行一次
            desc = "每隔" + cycle + "月的1日0点触发一次任务";
            break;
        case 5:// 每天cycle点执行一次
            desc = "每天" + cycle + "点执行一次任务";
            break;
        default:// 默认每cycle秒执行一次
            desc = "每隔" + cycle + "秒触发一次任务";
            break;
        }
        return desc;
    }

    // 参考例子
    public static void main(String[] args) {

        System.out.println(CronUtil.createLoopCronExpression(5, 1));
        System.out.println(CronUtil.createLoopCronDescription(5, 1));
        // 执行时间：每天的12时12分12秒 end

    }
}
