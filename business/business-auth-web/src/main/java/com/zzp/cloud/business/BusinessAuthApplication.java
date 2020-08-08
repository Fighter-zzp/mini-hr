package com.zzp.cloud.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 认证web主接口
 * <p>
 *  //TODO
 *  BusinessAuthApplication.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/27 22:04
 * @see  BusinessAuthApplication
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class BusinessAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessAuthApplication.class, args);
    }
}
