package com.video.external.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis 一些基本配置
 *
 * @author: master
 * @date: 2018/8/15
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    /**
     * 设置Redis命令执行模板
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /*
     * 使用单例Redis实现分布式锁。
     * 第一步：创建一个key并将值设置成一个和获取到锁的request有关的值，保证锁一定是有获得锁的request释放掉。
     * 第二步：将这个key设置过期时间，保证锁一定会被释放掉。
     * 第三步：释放掉锁后，将锁删除，保证其余request可以获取到锁。
     * 第四步：所用命令包括：SEXNX(如果已经存在这个key，将无法创建),GETSET(设置新值后返回旧值),expire(过期时间)
     * */
}
