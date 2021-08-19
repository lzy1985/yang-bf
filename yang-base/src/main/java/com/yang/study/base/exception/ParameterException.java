package com.yang.study.base.exception;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2020/11/9 20:20
 * @Description 参数异常
 */
public class ParameterException extends RuntimeException {

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(Throwable e) {
        super(e);
    }

    public ParameterException(String message, Throwable e) {
        super(message, e);
    }
}