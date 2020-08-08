package com.zzp.cloud.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 *
 * <p>
 *  //TODO
 *  BusinessWebsocketApplication.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/6 13:39
 * @see  com.zzp.cloud.business
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BusinessWebsocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessWebsocketApplication.class, args);
    }
}
