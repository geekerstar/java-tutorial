package com.geekerstar.arithmetic;

/**
 * @author geekerstar
 * date: 2019/3/2 17:22
 * description:
 *
 * 不用加法求两数之和
 *
 * 那么加法运算可以这样实现：
 * 1）先不考虑进位，按位计算各位累加（用异或实现），得到a;
 * 2）然后计算进位，并将进位的值左移，得到值b。若b为0，则a就是加法运算的结果；若b不为0，则a+b即得结果（递归调用这个过程）。实现算法如下：
 */
public class BitAdd {
    public int bitAdd(int a,int b )
    {
        if(b==0)
        {
            return a;
        }
        int sum=a^b;
        int carry=(a&b)<<1;
        return bitAdd(sum,carry);
    }
}
