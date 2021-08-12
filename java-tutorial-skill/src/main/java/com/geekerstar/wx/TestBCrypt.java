package com.geekerstar.wx;

import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * https://mp.weixin.qq.com/s/Pk0pBXhA8RRjSvMARSxvAg
 */
public class TestBCrypt {

    public static void main(String[] args) {
        String password = "123456";
        long md5Begin = System.currentTimeMillis();
        DigestUtil.md5Hex(password);
        long md5End = System.currentTimeMillis();
        System.out.println("md5 time:"+(md5End - md5Begin));
        long bcrytBegin = System.currentTimeMillis();
        BCrypt.hashpw(password, BCrypt.gensalt(10));
        long bcrytEnd = System.currentTimeMillis();
        System.out.println("bcrypt Time:" + (bcrytEnd- bcrytBegin));
    }
}
