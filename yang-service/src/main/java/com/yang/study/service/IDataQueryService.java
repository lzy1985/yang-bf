package com.yang.study.service;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/11 09:55
 * @Description
 */
public interface IDataQueryService {

    Object queryData(Object param, String methodName);

    void changeConfiguration();
}
