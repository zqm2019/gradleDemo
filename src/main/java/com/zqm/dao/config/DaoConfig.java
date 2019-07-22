package com.zqm.dao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

/**
 * dao层配置
 */
@Configuration
@MapperScan(basePackages = {"com.zqm.dao.mapper"})
public class DaoConfig {
}
