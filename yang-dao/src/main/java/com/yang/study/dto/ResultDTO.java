package com.yang.study.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/11 14:41
 * @Description TODO
 */
@Data
public class ResultDTO implements Serializable {

    private String columnValue;
    private String propertyValue;
    private String dataType;

}
