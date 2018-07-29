package edu.zhoutt.mall.common;

/**
 * 商品是否下架
 */
public enum IsDown {

    NORMAL(0, "正常"), DOWNED(1, "已被下架");

    int code;
    String desc;

    IsDown(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static IsDown fromCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (IsDown isDown : IsDown.values()) {
            if (isDown.getCode() == code) {
                return isDown;
            }
        }
        return null;
    }
}
