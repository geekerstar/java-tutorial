package com.geekerstar.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author geekerstar
 * date: 2019/11/17 23:13
 * description:
 */
public class Base64Test {
    public static void main(String[] args) {
        String text = "Geekerstar";

        // 转码
        String encode = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encode);

        // 解码
        String decode = new String(Base64.getDecoder().decode(encode), StandardCharsets.UTF_8);
        System.out.println(decode);


    }
}
