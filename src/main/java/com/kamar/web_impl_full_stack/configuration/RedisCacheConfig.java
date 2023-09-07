package com.kamar.web_impl_full_stack.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.UUID;

/**
 * configuration for caching on the Redis server.
 * @author kamar baraka.*/


@Configuration
public class RedisCacheConfig {

    /*register Redis connection factory*/
    @Bean
    public JedisConnectionFactory redisConnectionFactory(){

        /*construct redis server configuration*/
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

        /*set the properties*/
        configuration.setHostName("redis-18649.c9.us-east-1-4.ec2.cloud.redislabs.com");
        configuration.setDatabase(0);
        configuration.setPort(18649);
        configuration.setUsername("default");
        configuration.setPassword("iebUClyXaP1lfRGvQcdUihbRGkJAxTX9");

        /*construct and return the connection factory*/
        return new JedisConnectionFactory(configuration);
    }

    /*register the redis template to interact with the server*/
    @Bean
    public RedisTemplate<UUID, Object> redisTemplate(){

        /*construct the template*/
        RedisTemplate<UUID, Object> template = new RedisTemplate<>();

        /*set the connection factory*/
        template.setConnectionFactory(redisConnectionFactory());

        /*configure the serializers*/
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());

        /*set enable transaction support*/
        template.setEnableTransactionSupport(true);

        /*compile the configuration*/
        template.afterPropertiesSet();

        /*return the template*/
        return template;
    }
}
