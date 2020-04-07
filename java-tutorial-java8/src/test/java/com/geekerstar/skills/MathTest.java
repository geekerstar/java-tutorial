package com.geekerstar.skills;

import org.junit.Test;

import java.util.OptionalInt;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author geekerstar
 * @date 2020/4/7 14:49
 * @description
 */
public class MathTest {

    @Test
    public void gcd_of_array_containing_1_to_5_is_1() throws Exception {
        OptionalInt gcd = MathSnippets.gcd(new int[]{1, 2, 3, 4, 5});
        assertThat(gcd).isNotEmpty();
        assertThat(gcd).hasValue(1);
    }

    @Test
    public void gcd_of_array_containing_4_8_and_12_is_4() throws Exception {
        OptionalInt gcd = MathSnippets.gcd(new int[]{4, 8, 12});
        assertThat(gcd).isNotEmpty();
        assertThat(gcd).hasValue(4);
    }

    @Test
    public void lcm_of_array_containing_1_to_5_is_60() throws Exception {
        OptionalInt lcm = MathSnippets.lcm(new int[]{1, 2, 3, 4, 5});
        assertThat(lcm).isNotEmpty();
        assertThat(lcm).hasValue(60);
    }

    @Test
    public void lcm_of_array_containing_4_8_and_12_is_24() throws Exception {
        OptionalInt lcm = MathSnippets.lcm(new int[]{4, 8, 12});
        assertThat(lcm).isNotEmpty();
        assertThat(lcm).hasValue(24);
    }

    @Test
    public void average_of_1_to_10_is_5_dot_5() throws Exception {
        double average = MathSnippets.average(IntStream.rangeClosed(1, 10).toArray());
        assertThat(average).isEqualTo(5.5);
    }

    @Test
    public void findNextPositivePowerOfTwo_test() throws Exception {
        assertThat(MathSnippets.findNextPositivePowerOfTwo(-1)).isEqualTo(1);
        assertThat(MathSnippets.findNextPositivePowerOfTwo(3)).isEqualTo(4);
        assertThat(MathSnippets.findNextPositivePowerOfTwo(31)).isEqualTo(32);
        assertThat(MathSnippets.findNextPositivePowerOfTwo(32)).isEqualTo(32);
    }

    @Test
    public void isEven_test() throws Exception {
        assertThat(MathSnippets.isEven(1)).isFalse();
        assertThat(MathSnippets.isEven(2)).isTrue();
        assertThat(MathSnippets.isEven(3)).isFalse();
        assertThat(MathSnippets.isEven(4)).isTrue();
        assertThat(MathSnippets.isEven(-1)).isFalse();
    }

}
