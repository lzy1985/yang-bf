package com.yang.study.web.base.dto;

import com.yang.study.web.base.enums.ErrorCodeEnum;

import java.io.Serializable;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/12 09:14
 * @Description TODO
 */
@Data
@AllArgsConstructor
public class BaseResponseResultDTO<T> implements Serializable {

    private String code;
    private String msg;
    private Long ts;
    private Object data;

    public <T> BaseResponseResultDTO(String code, String name, long ts) {
        this.code = code;
        this.msg = name;
        this.ts = ts;
    }

    public static BaseResponseResultDTO SUCCESS() {
        return SUCCESS(null);
    }

    public static <T> BaseResponseResultDTO SUCCESS(T t) {
        return new BaseResponseResultDTO(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getName(), System.currentTimeMillis(), t);
    }


    public static BaseResponseResultDTO ERROR() {
        return ERROR(ErrorCodeEnum.ERROR_3000.getName());
    }

    public static BaseResponseResultDTO ERROR(String msg) {
        return new BaseResponseResultDTO(ErrorCodeEnum.ERROR_3000.getCode(), msg, System.currentTimeMillis(), null);
    }


    public static BaseResponseResultDTO ERROR(ErrorCodeEnum errorCodeEnum,String msg) {
        return new BaseResponseResultDTO(errorCodeEnum.getCode(), msg, System.currentTimeMillis(), null);
    }

    public static BaseResponseResultDTO ERROR(ErrorCodeEnum errorCodeEnum) {
        return ERROR(errorCodeEnum, null);
    }

    public static <T> BaseResponseResultDTO ERROR(ErrorCodeEnum errorCodeEnum, T t) {
        return new BaseResponseResultDTO(errorCodeEnum.getCode(), errorCodeEnum.getName(), (Long) System.currentTimeMillis(), t);
    }

}
