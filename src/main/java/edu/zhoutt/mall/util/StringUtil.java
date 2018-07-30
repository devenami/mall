package edu.zhoutt.mall.util;

public class StringUtil {

    public static boolean isNull(String str) {
        return null == str;
    }

    public static boolean notNull(String str) {
        return null != str;
    }

    public static boolean hasText(String str) {
        return notNull(str) && !"".equals(str.trim());
    }
}
