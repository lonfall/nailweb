package com.lh.nailweb.config;

import com.lh.nailweb.util.SnowFlakeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: loneyfall
 * @date: 2019/8/9
 * @description: 雪花算法配置项
 */
@Configuration
public class SnowFlakeConfig {

    @Value("${snow-flake.datacenter-id}")
    private long datacenterId;

    @Value("${snow-flake.machine-id}")
    private long machineId;

    @Bean
    public SnowFlakeUtil snowFlakeUtil() {
        return new SnowFlakeUtil(datacenterId, machineId);
    }
}
