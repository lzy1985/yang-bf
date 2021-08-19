package com.yang.study.base.config.base;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/9 10:45
 * @Description TODO
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.base.datasource")
@PropertySource(value = "classpath:db.properties")
public class BaseHikariConfig {
    private String driverClassName;
    private String jdbcUrl;
    private String username;
    private String password;
    private int maximumPoolSize;
    private int minimumIdle;
    private String poolName;
    private int validationTimeout;
    private int connectionTimeout;
}
