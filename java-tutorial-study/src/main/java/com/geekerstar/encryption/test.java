package com.geekerstar.encryption;

import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class test {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String password = "123456";
        String shaPwdString =new sun.misc.BASE64Encoder().encode(java.security.MessageDigest.getInstance("SHA1").digest(password.getBytes()));
        System.out.println(shaPwdString.substring(0,shaPwdString.length() - 4));

        byte[] by = password.getBytes();
        System.out.println(by);
        byte[] sha1s = MessageDigest.getInstance("SHA1").digest(by);
        System.out.println(sha1s);
        System.out.println(Arrays.toString(sha1s));

        String encode = new BASE64Encoder().encode(sha1s);
        System.out.println(encode);


    }
}
