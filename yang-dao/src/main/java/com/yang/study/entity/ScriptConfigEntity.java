package com.yang.study.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/11 13:01
 * @Description 脚本配置表
 */
@Data
public class ScriptConfigEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 命名空间名称
     */
    private String namespaceName;

    /**
     * 命名空间描述
     */
    private String namespaceDesc;

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
