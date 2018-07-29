package edu.zhoutt.mall.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 */
@Getter
@AllArgsConstructor
enum OrderStatus {

    NOT_PAY(0, "未付款"),
    PAIED(1, "已付款未发货"),
    SENDED(2, "已发货"),
    RECEIVED(3, "已收货");

    int code;
    String msg;

    public static OrderStatus fromCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (code == orderStatus.getCode()) {
                return orderStatus;
            }
        }
        return null;
    }
}
