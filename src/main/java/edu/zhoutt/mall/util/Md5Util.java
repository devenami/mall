package edu.zhoutt.mall.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.Objects;

public class Md5Util {

    /**
     * Md5 加密
     */
    public static String encode(String src) {

        int hash = Objects.hash(src);

        byte[] bytes = DigestUtils.md5(hash + "");

        int len = bytes.length;
        int cycle = len / 3;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cycle; i++) {
            byte one = bytes[i * 3];
            byte two = bytes[i * 3 + 1];
            byte three = bytes[i * 3 + 2];

            sb.append(one + two + three);
        }

        if (len % 3 != 0) {
            int m = len % 3;
            byte count = 0;
            for (int i = len - 1; i < len - 1 - m; i--) {
                byte b = bytes[i];
                count += b;
            }
            sb.append(count);
        }

        return sb.toString().replace("-", "");
    }

}
