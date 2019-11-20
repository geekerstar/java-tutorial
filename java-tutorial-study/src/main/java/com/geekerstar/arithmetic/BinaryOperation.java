package com.geekerstar.arithmetic;

/**
 * @author geekerstar
 * date: 2019/3/2 15:48
 * description:
 *
 * 阿里蚂蚁金服的面试中出现：要求手写实现“乘除法”
 * 度小满金融的面试中出现过：自己实现Math.sqrt函数
 * 快手的面试中曾出现：使用位运算实现整数加法运算
 *
 */
public class BinaryOperation {
    public static void main(String[] args) throws Exception {
        System.out.println(binaryAdd(-6, -15));
        System.out.println(binaryMulti(5, 6));
        System.out.println(binaryMulti2(5, 6));
        System.out.println(binaryDiv(6, 3));
    }
    //：a+b
    // 正负数都包含在里面，不用分开处理
    private static int binaryAdd(int a, int b) {
        int s = a ^ b;// 不考虑进位的和
        int jw = a & b;// 进位

        // 下面是 s + (jw<<1) 的计算，当进位为0时，不进位的和就是最终的计算结果
        while (jw != 0) {

            // 计算s + (jw<<1)的进位
            int jw_temp = s & (jw << 1);

            // 计算s + (jw<<1)的和，不包含进位
            s = s ^ (jw << 1);

            //计算进位以及不进位的和
            jw = jw_temp;
        }
        return s;
    }

    // 计算a*b
    private static int binaryMulti(int a, int b) {
        if (a == 0 || b == 0)
            return 0;

        int res = 0;
        int base = a;
        while (b != 0) {
            if ((b & 1) != 0)
                res = binaryAdd(res, base);
            b >>= 1;
            base <<= 1;
        }

        return res;
    }

    // 计算a*b
    private static int binaryMulti2(int a, int b) {
        if (a == 0 || b == 0)
            return 0;

        if(b>a) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        int res = 0;
        int shift = 0;
        while(b!=0) {
            if((b&1)!=0) {
                res += (a<<shift);
            }
            shift += 1;
            b >>= 1;
        }
        return res;
    }

    //：a / b
//模拟小时候初学除法时的运算过程，看代码之前先在草稿纸进行一次完整的除法计算
    private static int binaryDiv(int a, int b) throws Exception {
        if (b == 0)
            throw new Exception("分母不能为0");

        boolean flag = false;
        if ((a ^ b) < 0)
            flag = true;// 表示a,b异号；
        a = a >= 0 ? a : -a;
        b = b >= 0 ? b : -b;

        int res = 0;
        int aux = 0;//依次获取a的最高位
        int mask = 0x40_00_00_00;// 用来依次获取分母的最高位bit
        while (mask != 0) {
            aux = (aux << 1) | ((a & mask) == 0 ? 0 : 1);

            if (aux >= b) {
                res = (res << 1) | 1;
                aux -= b;
            } else {
                res = (res << 1) | 0;
            }
            mask >>= 1;
        }
        return flag ? -res : res;
    }

    //计算：Math.sqrt(num)
//原理：牛顿迭代法：
//https://baike.baidu.com/item/%E7%89%9B%E9%A1%BF%E8%BF%AD%E4%BB%A3%E6%B3%95/10887580?fr=aladdin
    private static double mySqrt(int num) {
        double x0 = num;

        double delta = 1e-12;//精度
        int count = 0;
        while(x0*x0-num>delta) {
            x0 = (x0*x0+num) / (2*x0);
        }
        return Math.round(x0*1000)/1000.0;//保留三位小数
    }
}
