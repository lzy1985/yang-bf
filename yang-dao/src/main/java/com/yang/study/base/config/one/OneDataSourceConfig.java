package com.yang.study.base.config.one;

import com.yang.study.base.config.base.BaseSqlSessionFactoryBean;
import com.yang.study.base.intercetor.SqlInterceptor;
import com.yang.study.base.util.DataSourceUtil;
import com.yang.study.base.util.DynamicSqlUtil;
import com.yang.study.mapper.base.IScriptConfigMapper;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
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
 * @Description TODO
 */
@Slf4j
@Configuration
@MapperScan(basePackages = "com.yang.study.**.mapper.one", sqlSessionFactoryRef = "oneSqlSessionFactory")
public class OneDataSourceConfig {

    @Resource
    private OneHikariConfig oneHikariConfig;

    @Resource
    private IScriptConfigMapper scriptConfigMapper;

    @Bean(name = "oneDataSource")
    public HikariDataSource dataSource() {
        return DataSourceUtil.buildOneHikariConfig(oneHikariConfig);
    }

    @Bean(name = "OneTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("oneDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "oneSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("oneDataSource") DataSource dataSource) throws Exception {
        BaseSqlSessionFactoryBean sessionFactoryBean = new BaseSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/one/*.xml"));
        sessionFactoryBean.setPlugins(new Interceptor[]{DataSourceUtil.buildPageHelper(),new SqlInterceptor()});
        org.apache.ibatis.session.Configuration configuration = sessionFactoryBean.buildConfiguration();
        configuration = DynamicSqlUtil.buildConfiguration(configuration,scriptConfigMapper,null);
        sessionFactoryBean.setConfiguration(configuration);
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "oneSqlSessionTemplate")
    public SqlSessionTemplate dbOneSqlSessionTemplate(@Qualifier("oneSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }



}
