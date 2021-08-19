package com.yang.study.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/11 13:01
 * @Description 脚本配置明细表
 */
@Data
public class ScriptConfigDetailEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 配置表id
     */
    private Integer configId;

    /**
     * 方法类型
     */
    private String methodType;

    /**
     * 方法id
     */
    private String methodId;

    /**
     * 方法描述
     */
    private String methodName;

    /**
     * 查询sql
     */
    private String methodSql;

    /**
     * 方法参数集
     */
    private String paramMap;

    /**
     * 方法返回集
     */
    private String resultMap;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;
}
