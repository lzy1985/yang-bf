package com.yang.study.service.impl;

import com.yang.study.base.util.DynamicSqlUtil;
import com.yang.study.mapper.base.IScriptConfigMapper;
import com.yang.study.service.IDataQueryService;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/11 09:55
 * @Description
 */
@Service
public class DataQueryServiceImpl implements IDataQueryService {

    @Resource
    SqlSessionFactory oneSqlSessionFactory;

    @Resource
    private IScriptConfigMapper scriptConfigMapper;

    @Override
    public Object queryData(Object param, String methodName) {
        return oneSqlSessionFactory.openSession().selectList(methodName,param);
    }

    @Override
    public void changeConfiguration() {
        Configuration configuration = oneSqlSessionFactory.getConfiguration();
        DynamicSqlUtil.buildConfiguration(configuration,scriptConfigMapper,1);
    }
}
