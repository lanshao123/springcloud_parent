package com.wh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: springcloud_parent
 * @description: NacosConsumerApp
 * @author: wangheng
 * @create: 2022-07-25 17:16
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApp.class,args);
    }
}
