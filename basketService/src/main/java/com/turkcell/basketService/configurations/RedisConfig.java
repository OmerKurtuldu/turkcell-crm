package com.turkcell.basketService.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10); // havuzdaki maximum bağlantı sayısı
        poolConfig.setMaxIdle(5);  // Havuzda boşta bekleyecek maximum bağlantı sayısı
        poolConfig.setMinIdle(1); // Havuzda boşta bekleyecek minimum bağlantı sayısı
        return new JedisConnectionFactory(poolConfig);
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}