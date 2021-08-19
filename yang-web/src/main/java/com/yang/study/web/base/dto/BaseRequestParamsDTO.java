package com.yang.study.web.base.dto;


import java.io.Serializable;
import java.util.HashMap;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/12 09:14
 * @Description TODO
 */
@Data
public class BaseRequestParamsDTO<T> implements Serializable {

    @NotNull(message = "请求编号不能为空")
    private String requestId;
    @NotNull(message = "系统名不能为空")
    private String sys;
    @NotNull(message = "方法名不能为空")
    private String methodName;
    @NotNull(message = "时间戳不能为空")
    private Long ts;
    private Object data;

}
