package com.geekerstar.wx;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author geekerstar
 * @date 2021/7/26 10:35
 * @description  BigDecimal
 *
 * https://mp.weixin.qq.com/s?__biz=MzU2MTI4MjI0MQ==&mid=2247488306&idx=3&sn=5d000fa00aa3b30dc8bcb8c6f894a085&chksm=fc7a7e9ccb0df78a8444ad16551ba8f53c392d26b6a6bd6327d8d4e4b6c3c4a0fcc1c4564374&mpshare=1&scene=1&srcid=&sharer_sharetime=1574312020070&sharer_shareid=535c00d0d7095600f2fcdf96cc5a31ba#rd
 */
public class Test11 {

    public static BigDecimal add(double v1, double v2) {// v1 + v2
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        // 2 = 保留小数点后两位   ROUND_HALF_UP = 四舍五入
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);// 应对除不尽的情况
    }

    /**
     *  Java 小数点位数保留
     *
     *  https://mp.weixin.qq.com/s?__biz=MzU2MTI4MjI0MQ==&mid=2247488292&idx=2&sn=4169105d1842f7e026d467f20e1e344a&chksm=fc7a7e8acb0df79cef301cf5d61fffe973d1aeeb9a68acfabd444ef4ef6bf086df75797209a6&mpshare=1&scene=1&srcid=&sharer_sharetime=1574177532443&sharer_shareid=535c00d0d7095600f2fcdf96cc5a31ba#rd
     */
    @Test
    public void test1(){
        double d = 0.200;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(df.format(d));

        // 若是这种写法DecimalFormat df = new DecimalFormat("#.##")，则保留小数点后面不为0的两位小数，这种写法不能保证保留2为小数，但能保证最后一位数不为0
        double d1 = 41.123;
        DecimalFormat df1 = new DecimalFormat("#.##");
        System.out.println(df1.format(d1));

        double d2 = 0.6544;
        String s=String.format("%.2f",d2);
        System.out.println(s);

        double d3 = 1.000;
        BigDecimal bd=new BigDecimal(d3);
        double d13=bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(d13);
    }
}
