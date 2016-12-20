package com.cookabuy.configuration;

import com.cookabuy.properties.ESProperties;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yejinbiao
 * @create 2016-12-19-上午9:53
 */

@Slf4j
@Configuration
@EnableConfigurationProperties(ESProperties.class)
public class ElasticSearchConfiguration {

//    @Bean
//    public TransportClient transportClient(ESProperties properties){
//        log.info("es properties {}",properties);
//        TransportClient client = null;
//        try {
//             client = new PreBuiltTransportClient(Settings.EMPTY)
//                     .addTransportAddress(
//                             new InetSocketTransportAddress(
//                                     InetAddress.getByName(properties.getHost()), properties.getPort()
//                             )
//                     );
//        } catch (UnknownHostException e) {
//            log.info("the elasticsearch host is unknown,please check the property {elasticsearch.host} in application.properties");
//        }
//        return client;
//    }
}
