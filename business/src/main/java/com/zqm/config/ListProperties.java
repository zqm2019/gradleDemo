package com.zqm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @describe:
 * @author:zqm
 */
@PropertySource(value = "classpath:list.properties")
@ConfigurationProperties(prefix = "data")
@Component
@Data
public class ListProperties {
    private Map<String,String> student;
    private Map<String,String> person;
    private List<String> list;
    private List<String> newList;

}
