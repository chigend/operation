package com.cookabuy.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yejinbiao
 * @create 2016-12-19-上午9:56
 */
@ConfigurationProperties(prefix = "elasticsearch")
@Getter
@Setter
public class ESProperties {
    private String host;

    private int port;
}
