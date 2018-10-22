package com.ferhat.multitenant.config;

import com.ferhat.multitenant.TenantContext;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoClientUri;

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoDbFactory(mongoClient));
    }


    private MultiTenantMongoDbFactory mongoDbFactory(MongoClient mongoClient) {
        return new MultiTenantMongoDbFactory(mongoClient, TenantContext.getTenant(),mongoClientUri);
    }
}
