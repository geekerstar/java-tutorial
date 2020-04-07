package com.geekerstar.skills;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author geekerstar
 * @date 2020/4/7 10:36
 * @description
 */
public abstract class StringSnippets {

    /**
     * 生成一个字符串的所有字符（包含重复）。
     *
     * @param input
     * @return
     */
    public static List<String> anagrams(String input) {
        if (input.length() <= 2) {
            return input.length() == 2
                    ? Arrays.asList(input, input.substring(1) + input.substring(0, 1))
                    : Collections.singletonList(input);
        }
        return IntStream.range(0, input.length())
                .mapToObj(i -> new SimpleEntry<>(i, input.substring(i, i + 1)))
                .flatMap(entry ->
                        anagrams(input.substring(0, entry.getKey()) + input.substring(entry.getKey() + 1))
                                .stream()
                                .map(s -> entry.getValue() + s))
                .collect(Collectors.toList());
    }


    /**
     * 以字节为单位返回字符串的长度。
     *
     * @param input
     * @return
     */
    public static int byteSize(String input) {
        return input.getBytes().length;
    }

    /**
     * 将字符串首字母大写。
     *
     * @param input
     * @param lowerRest
     * @return
     */
    public static String capitalize(String input, boolean lowerRest) {
        return input.substring(0, 1).toUpperCase() +
                (lowerRest
                        ? input.substring(1, input.length()).toLowerCase()
                        : input.substring(1, input.length()));
    }

    /**
     * 将字符串中每个单词的首字母大写。
     *
     * @param input
     * @return
     */
    public static String capitalizeEveryWord(final String input) {
        return Pattern.compile("\\b(?=\\w)").splitAsStream(input)
                .map(w -> capitalize(w, false))
                .collect(Collectors.joining());
    }

    /**
     * 在提供的字符串中返回元音的个数。
     *
     * @param input
     * @return
     */
    public static int countVowels(String input) {
        return input.replaceAll("[^aeiouAEIOU]", "").length();
    }

    /**
     * 转义要在正则表达式中使用的字符串。
     *
     * @param input
     * @return
     */
    public static String escapeRegExp(String input) {
        return Pattern.quote(input);
    }

    /**
     * 从驼峰式转换字符串。
     *
     * @param input
     * @param separator
     * @return
     */
    public static String fromCamelCase(String input, String separator) {
        return input
                .replaceAll("([a-z\\d])([A-Z])", "$1" + separator + "$2")
                .toLowerCase();
    }

    public static boolean isAbsoluteUrl(String url) {
        return Pattern.compile("^[a-z][a-z0-9+.-]*:").matcher(url).find();
    }

    /**
     * 检查字符串是否为小写。
     *
     * @param input
     * @return
     */
    public static boolean isLowerCase(String input) {
        return Objects.equals(input, input.toLowerCase());
    }

    /**
     * 检查字符串是否为大写。
     *
     * @param input
     * @return
     */
    public static boolean isUpperCase(String input) {
        return Objects.equals(input, input.toUpperCase());
    }

    /**
     * 判断一个字符串是否回文。
     *
     * @param input
     * @return
     */
    public static boolean isPalindrome(String input) {
        String s = input.toLowerCase().replaceAll("[\\W_]", "");
        return Objects.equals(
                s,
                new StringBuilder(s).reverse().toString()
        );
    }

    /**
     * 检查字符串是否为数字。
     *
     * @param input
     * @return
     */
    public static boolean isNumeric(final String input) {
        return IntStream.range(0, input.length())
                .allMatch(i -> Character.isDigit(input.charAt(i)));
    }

    /**
     * 用指定的掩码字符替换除最后 num 个字符以外的所有字符。
     *
     * @param input
     * @param num
     * @param mask
     * @return
     */
    public static String mask(String input, int num, String mask) {
        int length = input.length();
        return num > 0
                ?
                input.substring(0, length - num).replaceAll(".", mask)
                        + input.substring(length - num)
                :
                input.substring(0, Math.negateExact(num))
                        + input.substring(Math.negateExact(num), length).replaceAll(".", mask);
    }

    /**
     * 反转字符串。
     *
     * @param input
     * @return
     */
    public static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * 按字母顺序排列字符串中的字符。
     *
     * @param input
     * @return
     */
    public static String sortCharactersInString(String input) {
        return Arrays.stream(input.split("")).sorted().collect(Collectors.joining());
    }

    /**
     * 将多行字符串拆分为行数组。
     *
     * @param input
     * @return
     */
    public static String[] splitLines(String input) {
        return input.split("\\r?\\n");
    }


    /**
     * 转换一个字符串为驼峰式。
     *
     * @param input
     * @return
     */
    public static String toCamelCase(String input) {
        Matcher matcher = Pattern.compile("[A-Z]{2,}(?=[A-Z][a-z]+[0-9]*|\\b)|[A-Z]?[a-z]+[0-9]*|[A-Z]|[0-9]+").matcher(input);
        List<String> matchedParts = new ArrayList<>();
        while (matcher.find()) {
            matchedParts.add(matcher.group(0));
        }
        String s = matchedParts.stream()
                .map(x -> x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase())
                .collect(Collectors.joining());
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    /**
     * 将字符串转换为kebab大小写。
     *
     * @param input
     * @return
     */
    public static String toKebabCase(String input) {
        Matcher matcher = Pattern.compile("[A-Z]{2,}(?=[A-Z][a-z]+[0-9]*|\\b)|[A-Z]?[a-z]+[0-9]*|[A-Z]|[0-9]+").matcher(input);
        List<String> matchedParts = new ArrayList<>();
        while (matcher.find()) {
            matchedParts.add(matcher.group(0));
        }
        return matchedParts.stream()
                .map(String::toLowerCase)
                .collect(Collectors.joining("-"));
    }

    /**
     * 正则匹配
     *
     * @param input
     * @param regex
     * @return
     */
    public static List<String> match(String input, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        List<String> matchedParts = new ArrayList<>();
        while (matcher.find()) {
            matchedParts.add(matcher.group(0));
        }
        return matchedParts;
    }

    /**
     * 将字符串转换为蛇形小写，如 Im_Biezhi
     *
     * @param input
     * @return
     */
    public static String toSnakeCase(String input) {
        Matcher matcher = Pattern.compile("[A-Z]{2,}(?=[A-Z][a-z]+[0-9]*|\\b)|[A-Z]?[a-z]+[0-9]*|[A-Z]|[0-9]+").matcher(input);
        List<String> matchedParts = new ArrayList<>();
        while (matcher.find()) {
            matchedParts.add(matcher.group(0));
        }
        return matchedParts.stream()
                .map(String::toLowerCase)
                .collect(Collectors.joining("_"));
    }

    /**
     * 将字符串截断到指定的长度。
     *
     * @param input
     * @param num
     * @return
     */
    public static String truncateString(String input, int num) {
        return input.length() > num
                ? input.substring(0, num > 3 ? num - 3 : num) + "..."
                : input;
    }

    /**
     * 将给定的字符串转换为单词数组。
     *
     * @param input
     * @return
     */
    public static String[] words(String input) {
        return Arrays.stream(input.split("[^a-zA-Z-]+"))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    /**
     * 将由空格分隔的数字字符串转换为 int 数组。
     *
     * @param numbers
     * @return
     */
    public static int[] stringToIntegers(String numbers) {
        return Arrays.stream(numbers.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static int[] randomInts(int total, int start, int end) {
        return ThreadLocalRandom.current().ints(total, start, end).toArray();
    }
}
