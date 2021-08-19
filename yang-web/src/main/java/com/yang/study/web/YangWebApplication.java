package com.yang.study.web;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude= {MybatisAutoConfiguration.class,DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.yang.study"})
public class YangWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangWebApplication.class, args);
    }

}
