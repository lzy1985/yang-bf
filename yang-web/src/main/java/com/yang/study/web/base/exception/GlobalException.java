package com.yang.study.web.base.exception;

import com.yang.study.base.exception.BusinessException;
import com.yang.study.base.exception.ParameterException;
import com.yang.study.web.base.dto.BaseResponseResultDTO;
import com.yang.study.web.base.enums.ErrorCodeEnum;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/12 18:08
 * @Description TODO
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {
    /**
     * 参数校验不通过
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResponseResultDTO argumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
        // 获得BindingResult
        BindingResult result = e.getBindingResult();
        // 遍历出校验不通过的变量名及其校验信息，拼接成错误信息
        List<FieldError> errors = result.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError error : errors) {
            sb.append(" [ ").append(error.getField()).append(":").append(error.getDefaultMessage()).append(" ]");
        }
        // 输出至响应体及控制台
        String errorResult = sb.toString();
        log.error("参数校验不通过,请求URL:{},错误信息：{}", req.getRequestURL(), errorResult);
        return BaseResponseResultDTO.ERROR(errorResult);
    }

    /**
     * 参数异常处理
     */
    @ExceptionHandler(value = ParameterException.class)
    public BaseResponseResultDTO parameterExceptionHandler(HttpServletRequest req, Exception e) {
        log.error("参数异常,请求URL:{},错误信息：{}", req.getRequestURL(), e.getMessage());
        return BaseResponseResultDTO.ERROR(e.getMessage());
    }

    /**
     * 业务异常处理
     */
    @ExceptionHandler(value = BusinessException.class)
    public BaseResponseResultDTO businessExceptionHandler(HttpServletRequest req, Exception e) {
        log.error("业务异常,请求URL:{},错误信息：{}", req.getRequestURL(), e.getMessage());
//        sendMonitorMsg(e);
        return BaseResponseResultDTO.ERROR(e.getMessage());
    }

    /**
     * 系统异常处理
     */
    @ExceptionHandler(value = RuntimeException.class)
    public BaseResponseResultDTO exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("系统异常,请求URL:{},错误信息：", req.getRequestURL(),  e);
        return BaseResponseResultDTO.ERROR(ErrorCodeEnum.ERROR_9999.getName());
    }
}
