package com.wh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: springcloud_parent
 * @description: NacosProviderApp
 * @author: wangheng
 * @create: 2022-07-25 16:53
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApp.class,args);
    }
}
