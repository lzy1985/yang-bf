package com.yang.study.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/11 14:36
 * @Description TODO
 */
@Data
public class ParamDTO implements Serializable {

    private String paramValue;
    private String paramType;
}
