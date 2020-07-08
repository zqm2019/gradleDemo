package com.zqm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 配置表
 *
 * @author zqm
 * @date:2018/12/12
 */
@PropertySource(value = "classpath:designers.yml", factory = YamFactory.class)
@ConfigurationProperties(prefix = "designer")
@Component
@Data
public class DesignProperties {
    private Map<String, List<String>> owner;
}
