package com.yang.study.base.exception;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2020/11/11 13:39
 * @Description 业务异常
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
    }
}
