package com.yang.study.web.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/12 09:14
 * @Description 错误码枚举
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    SUCCESS("1000", "成功"),
    FAIL("2000", "失败"),
    ERROR_3000("3000", "参数错误"),
    ERROR_9999("9999", "系统异常");

    private String code;
    private String name;

    public static ErrorCodeEnum getEnums(String code) {
        for (ErrorCodeEnum ec : values()) {
            if (ec.getCode().equals(code)) {
                return ec;
            }
        }
        return null;
    }

}
