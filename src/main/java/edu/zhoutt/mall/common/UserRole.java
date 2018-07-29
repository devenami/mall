package edu.zhoutt.mall.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户身份
 */
@Getter
@AllArgsConstructor
public enum UserRole {

    USER(0, "普通用户"),
    ADMIN(1, "管理员");

    int code;
    String msg;

    public static UserRole fromCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (UserRole userRole : UserRole.values()) {
            if (code == userRole.getCode()) {
                return userRole;
            }
        }
        return null;
    }
}
