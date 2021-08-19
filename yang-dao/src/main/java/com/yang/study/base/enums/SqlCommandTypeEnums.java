package com.yang.study.base.enums;

import org.apache.ibatis.mapping.SqlCommandType;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/12 15:06
 * @Description TODO
 */
@AllArgsConstructor
@Getter
public enum SqlCommandTypeEnums {
    UNKNOWN("", SqlCommandType.UNKNOWN),
    SELECT("select", SqlCommandType.SELECT),
    DELETE("delete", SqlCommandType.DELETE),
    UPDATE("update", SqlCommandType.UPDATE),
    INSERT("insert", SqlCommandType.INSERT),
    FLUSH("flush", SqlCommandType.FLUSH);


    private String methodType;
    private SqlCommandType sqlCommandType;

    public static SqlCommandType lookUpSqlCommandType(String methodType) {
        if(null== methodType){
            return SqlCommandType.UNKNOWN;
        }
        for (SqlCommandTypeEnums ec : values()) {
            if (ec.getMethodType().equals(methodType.toLowerCase())) {
                return ec.getSqlCommandType();
            }
        }
        return SqlCommandType.UNKNOWN;
    }

}
