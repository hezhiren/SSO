package cn.hzr0523.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * hezhi
 * 2018/5/10 14:55
 */
@Configuration
public class JedisConfig {

    @Value("${spring.redis.maxTotal}")
    public Integer maxTotal;

    @Value("${spring.redis.host}")
    public String host;

    @Value("${spring.redis.port}")
    public Integer port;


    @Bean
    public JedisPool jedisPool() {
        JedisPool jedisPool = new JedisPool(jedisPoolConfig(), host, port, 30);
        return jedisPool;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        return jedisPoolConfig;
    }
}
