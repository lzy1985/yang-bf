package com.yang.study.base.util;

import com.github.pagehelper.PageHelper;
import com.yang.study.base.config.base.BaseHikariConfig;
import com.yang.study.base.config.one.OneHikariConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/11 10:03
 * @Description
 */
public class DataSourceUtil {

    public static PageHelper buildPageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "false");// RowBounds参数offset作为PageNum使用 默认不使用
        properties.setProperty("rowBoundsWithCount", "false"); // RowBounds是否进行count查询 - 默认不查询
        properties.setProperty("pageSizeZero", "true");// 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
        properties.setProperty("reasonable", "false");// 分页合理化
        properties.setProperty("supportMethodsArguments", "false");// 是否支持接口参数来传递分页参数，默认false
        properties.setProperty("returnPageInfo", "none");

        pageHelper.setProperties(properties);
        return pageHelper;
    }

    public static HikariDataSource buildHikariConfig(BaseHikariConfig hikariConfig){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(hikariConfig.getDriverClassName());
        config.setJdbcUrl(hikariConfig.getJdbcUrl());
        config.setUsername(hikariConfig.getUsername());
        config.setPassword(hikariConfig.getPassword());
        config.setMaximumPoolSize(hikariConfig.getMaximumPoolSize());
        config.setMinimumIdle(hikariConfig.getMinimumIdle());
        config.setPoolName(hikariConfig.getPoolName());
        config.setValidationTimeout(hikariConfig.getValidationTimeout());
        config.setConnectionTimeout(hikariConfig.getConnectionTimeout());

        return new HikariDataSource(config);
    }
    public static HikariDataSource buildOneHikariConfig(OneHikariConfig onehikariConfig){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(onehikariConfig.getDriverClassName());
        config.setJdbcUrl(onehikariConfig.getJdbcUrl());
        config.setUsername(onehikariConfig.getUsername());
        config.setPassword(onehikariConfig.getPassword());
        config.setMaximumPoolSize(onehikariConfig.getMaximumPoolSize());
        config.setMinimumIdle(onehikariConfig.getMinimumIdle());
        config.setPoolName(onehikariConfig.getPoolName());
        config.setValidationTimeout(onehikariConfig.getValidationTimeout());
        config.setConnectionTimeout(onehikariConfig.getConnectionTimeout());

        return new HikariDataSource(config);
    }
}
