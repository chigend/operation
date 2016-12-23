package com.cookabuy.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯对象存储（cloud object service）cos相关的配置属性
 *
 * @author yejinbiao
 * @create 2016-12-23-15:24
 */
@ConfigurationProperties(prefix = "cos")
@Setter
@Getter
public class CosProperties {
    private long appID;

    private String secretID;

    private String secretKey;
}
