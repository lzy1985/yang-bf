package com.yang.study.mapper.base;

import com.yang.study.entity.ScriptConfigQueryEntity;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/11 13:00
 * @Description
 */
public interface IScriptConfigMapper {

    List<ScriptConfigQueryEntity> queryInitScriptConfig(@Param("id") Integer id);
}
