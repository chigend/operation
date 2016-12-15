package com.cookabuy.configuration;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author yejinbiao
 * @create 2016-12-15-上午10:22
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.cookabuy.search.repository")
public class ElasticsearchConfiguration {
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }

    @Bean
    public Client client(){
        TransportClient client= new TransportClient();
        TransportAddress address = new InetSocketTransportAddress("127.0.0.1", 9300);
        client.addTransportAddress(address);
        return client;
    }
}
