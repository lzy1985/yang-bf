package com.yang.study.base.config.base;

import com.yang.study.base.util.DataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/9 10:40
 * @Description
 */
@Slf4j
@Configuration
@MapperScan(basePackages = "com.yang.study.**.mapper.base", sqlSessionFactoryRef = "baseSqlSessionFactory")
public class BaseDateSourceConfig {

    @Resource
    private BaseHikariConfig baseHikariConfig;

    @Bean(name = "baseDataSource")
    public HikariDataSource dataSource() {
        return DataSourceUtil.buildHikariConfig(baseHikariConfig);
    }

    @Bean(name = "baseTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("baseDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "baseSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("baseDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/base/*.xml"));
        sessionFactoryBean.setPlugins(new Interceptor[]{DataSourceUtil.buildPageHelper()});
        return sessionFactoryBean.getObject();
    }
}
