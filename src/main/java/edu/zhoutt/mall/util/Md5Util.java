package edu.zhoutt.mall.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

public class Md5Util {

    private static final String salt = "";

    /**
     * Md5 加密
     */
    public static String encode(String src) {

        return Arrays.toString(DigestUtils.md5(src));
    }

}
