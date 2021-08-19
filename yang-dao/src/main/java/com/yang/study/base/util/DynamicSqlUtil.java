package com.yang.study.base.util;

import com.yang.study.base.config.base.SqlLanguageDriver;
import com.yang.study.base.enums.SqlCommandTypeEnums;
import com.yang.study.dto.ParamDTO;
import com.yang.study.dto.ResultDTO;
import com.yang.study.entity.ScriptConfigQueryEntity;
import com.yang.study.mapper.base.IScriptConfigMapper;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/11 14:00
 * @Description TODO
 */
@Slf4j
public class DynamicSqlUtil {

    /**
     * 构建configuration
     *
     * @param config
     * @return
     */
    public static org.apache.ibatis.session.Configuration buildConfiguration(org.apache.ibatis.session.Configuration config, IScriptConfigMapper scriptConfigMapper, Integer configDetailId) {
        List<ScriptConfigQueryEntity> list = scriptConfigMapper.queryInitScriptConfig(configDetailId);
        try {
            if (!CollectionUtils.isEmpty(list)) {
                for (ScriptConfigQueryEntity scriptConfigQuery : list) {
                    final ParameterMap parameterMap = DynamicSqlUtil.buildParameterMap(config, scriptConfigQuery.getMethodId(), scriptConfigQuery.getParamMap());
                    if (null != parameterMap) {
                        if (config.getParameterMapNames().contains(parameterMap.getId())) {
                            removeKey(config, "parameterMaps", parameterMap.getId());
                        }
                        config.addParameterMap(parameterMap);
                    }

                    final ResultMap tagResultMap = DynamicSqlUtil.buildResultMap(config, scriptConfigQuery.getMethodId(), scriptConfigQuery.getResultMap());
                    if (null != tagResultMap) {
                        if (config.getResultMapNames().contains(tagResultMap.getId())) {
                            removeKey(config, "resultMaps", tagResultMap.getId());
                        }
                        config.addResultMap(tagResultMap);
                    }
                    SqlSource sqlSource = DynamicSqlUtil.buildSqlSource(config, scriptConfigQuery.getMethodSql());
                    SqlCommandType sqlCommandType = SqlCommandTypeEnums.lookUpSqlCommandType(scriptConfigQuery.getMethodType());
                    MappedStatement mappedStatement = DynamicSqlUtil.buildMappedStatement(config, scriptConfigQuery.getMethodId(), sqlSource, sqlCommandType, parameterMap, tagResultMap);
                    if (null != mappedStatement) {
                        if (config.getMappedStatementNames().contains(mappedStatement.getId())) {
                            removeKey(config, "mappedStatements", mappedStatement.getId());
                        }
                        config.addMappedStatement(mappedStatement);
                    }
                }
            } else {
                log.info("初始化数据,无配置数据");
            }
        } catch (Exception e) {
            log.error("初始化动态加载配置信息错误，异常:{}", e);
            throw new RuntimeException("初始化动态加载配置信息错误，异常:" + e.getMessage());
        }
        return config;
    }

    /**
     * 构建参数映射对象集合
     *
     * @param config
     * @param statementId
     * @return
     */
    private static ParameterMap buildParameterMap(Configuration config, String statementId, String paramMap) throws ClassNotFoundException {
        List<ParamDTO> paramList = JsonUtil.toList(paramMap, ParamDTO.class);
        if (!CollectionUtils.isEmpty(paramList)) {
            List list = new ArrayList<ParameterMapping>();
            for (ParamDTO paramDTO : paramList) {
                list.add(buildParameterMapping(config, paramDTO.getParamValue(), Class.forName(paramDTO.getParamType())));

            }
            return new ParameterMap.Builder(config, statementId, HashMap.class, list).build();
        }
        return null;
    }

    /**
     * 构建参数映射对象集合
     *
     * @param config
     * @param statementId
     * @return
     */
    private static ResultMap buildResultMap(Configuration config, String statementId, String resultMap) throws ClassNotFoundException {
        List<ResultDTO> resultList = JsonUtil.toList(resultMap, ResultDTO.class);
        if (!CollectionUtils.isEmpty(resultList)) {
            List list = new ArrayList<ResultMapping>();
            for (ResultDTO resultDTO : resultList) {
                list.add(buildResultMapping(config, resultDTO.getPropertyValue(), resultDTO.getColumnValue(), Class.forName(resultDTO.getDataType())));
            }
            return new ResultMap.Builder(config, statementId, HashMap.class, list).build();
        }
        return null;
    }

    /**
     * 构建mappedStatement
     *
     * @param config
     * @param statementId
     * @param sqlSource
     * @param parameterMap
     * @param resultMap
     * @return
     */
    private static MappedStatement buildMappedStatement(Configuration config, String statementId, SqlSource sqlSource, SqlCommandType sqlCommandType, ParameterMap parameterMap, ResultMap resultMap) {
        MappedStatement.Builder builder = new MappedStatement.Builder(config, statementId, sqlSource, sqlCommandType);
        if (null != parameterMap) {
            builder.parameterMap(parameterMap);
        }
        if (null != resultMap) {
            builder.resultMaps(new ArrayList<ResultMap>() {{
                add(resultMap);
            }});
        }
        return builder.build();
    }

    /**
     * 构建sqlSource
     *
     * @param config
     * @param methodSql
     * @return
     * @throws SQLException
     */
    private static SqlSource buildSqlSource(Configuration config, String methodSql) throws SQLException {
        if (null == methodSql) {
            throw new SQLException("sql不存在");
        }
        SqlLanguageDriver langDriver = new SqlLanguageDriver();
        return langDriver.createSqlSource(config, methodSql, Map.class);
    }

    /**
     * 构建参数映射对象
     *
     * @param config
     * @param propertyName
     * @param c
     * @param <T>
     * @return
     */
    private static <T> ParameterMapping buildParameterMapping(Configuration config, String propertyName, Class<T> c) {
        final TypeHandlerRegistry registry = config.getTypeHandlerRegistry();
        return new ParameterMapping.Builder(config, propertyName, registry.getTypeHandler(c)).build();
    }


    /**
     * 构建返回映射对象
     *
     * @param config
     * @param propertyName
     * @param columnName
     * @param c
     * @param <T>
     * @return
     */
    private static <T> ResultMapping buildResultMapping(Configuration config, String propertyName, String columnName, Class<T> c) {
        final TypeHandlerRegistry registry = config.getTypeHandlerRegistry();
        return new ResultMapping.Builder(config, propertyName, columnName, registry.getTypeHandler(c)).build();
    }

    /**
     * 删除指定数据
     *
     * @param config
     * @param fieldName
     * @param key
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void removeKey(Configuration config, String fieldName, String key) throws NoSuchFieldException, IllegalAccessException {
        Field field = config.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Map<?, ?> map = (Map<?, ?>) field.get(config);
        map.remove(key);
        field.set(config, map);
    }
}
