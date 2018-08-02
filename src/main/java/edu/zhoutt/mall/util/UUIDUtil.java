package edu.zhoutt.mall.util;

import java.util.UUID;

public class UUIDUtil {

    public static String getUuid() {

        String string = UUID.randomUUID().toString();

        return string.replaceAll("-", "");
    }

}
