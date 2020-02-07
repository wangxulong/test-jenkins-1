package com.example.testjenkins.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

/**
 * TODO 简要描述
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20200115000
 */
@ConfigurationProperties(prefix = "app")
@Data
@Component
public class AppConfig {
    String name;
}
