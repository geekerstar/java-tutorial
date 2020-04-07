package com.geekerstar.skills;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

/**
 * @author geekerstar
 * @date 2020/4/7 10:32
 * @description
 */
public class MathSnippets {

    /**
     * 返回两个或两个以上数字的平均值。
     *
     * @param arr
     * @return
     */
    public static double average(int[] arr) {
        return IntStream.of(arr)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("Array is empty"));
    }


    /**
     * 计算一系列数字的最大公约数(gcd)。
     * 使用 Arrays.stream().reduce() 和 GCD（使用递归公式）计算一组数字的最大公约数。
     *
     * @param numbers
     * @return
     */
    public static OptionalInt gcd(int[] numbers) {
        return Arrays.stream(numbers)
                .reduce((a, b) -> gcd(a, b));
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * 计算数字数组的最低公共倍数(LCM)。
     * 使用 Arrays.stream().reduce() 和 LCM公式(使用递归)来计算数字数组的最低公共倍数。
     *
     * @param numbers
     * @return
     */
    public static OptionalInt lcm(int[] numbers) {
        IntBinaryOperator lcm = (x, y) -> (x * y) / gcd(x, y);
        return Arrays.stream(numbers)
                .reduce((a, b) -> lcm.applyAsInt(a, b));
    }


    /**
     * 查找大于或等于该值的下一个幂。
     * 该方法使用左移运算符将1与右侧的值位移。右侧使用 Integer.numberOfLeadingZeros方法。 001 << 2 would be 100. 100 in decimal is equal to 4.
     * Integer.numberOfLeadingZeros 给出了数值前导零的数目。例如，调用 Integer.numberOfLeadingZeros(3) 将赋值为30。 这是因为3在二进制中表示为 11。由于整数有32位，所以有30位有0位。左移运算符的右边变为 32-30 = 2。 左移1，即 001 << 2 将是 100，十进制中的 100 等于 4。
     *
     * @param value
     * @return
     */
    public static int findNextPositivePowerOfTwo(int value) {
        return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
    }

    /**
     * 检查数字是否是偶数。
     * 这个方法使用按位运算符，0b1 是1的二进制表示。 因为Java 7可以通过用 0b 或 0B 作为前缀来编写二进制文字。 数字为偶数时，＆ 运算符将返回0。 例如，IsEven(4) 会导致 100 & 001，＆ 的结果将是 000。
     *
     * @param value
     * @return
     */
    public static boolean isEven(final int value) {
        return (value & 0b1) == 0;
    }

    /**
     * 检查一个值是2的正幂。
     * 为了理解它是如何工作的，让我们假设我们调用了 IsPowerOfTwo(4)。
     * 当值大于0时，将评估 && 运算符的右侧。
     * (~value + 1) 的结果等于值本身，~100 + 001 => 011 + 001 => 100。
     * (value & value) 的结果是value，100 & 100 => 100.。
     * 当值等于值时，这将把值表达为真值。
     *
     * @param value
     * @return
     */
    public static boolean isPowerOfTwo(final int value) {
        return value > 0 && ((value & (~value + 1)) == value);
    }

    /**
     * 生成一个介于 Integer.MIN_VALUE 和 Integer.MAX_VALUE 之间的随机数。
     *
     * @return
     */
    public static int generateRandomInt() {
        return ThreadLocalRandom.current().nextInt();
    }
}
