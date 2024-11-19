package com.eatory.mvc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.eatory.mvc.model.dao")
public class DBConfig {

}
