package com.zzp.cloud.business;

import com.zzp.cloud.configuration.security.costom.CustomFilterInvocationSecurityMetadataSource;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 个人信息系统web接口
 * <p>
 *  //TODO
 *  BusinessCmsApplication.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 17:16
 * @see  com.zzp.cloud.business
 **/
@EnableFeignClients
@SpringBootApplication(
        scanBasePackageClasses = {BusinessPimsApplication.class}
        , scanBasePackages = {"com.zzp.cloud.configuration.security"})
@EnableDiscoveryClient
@DubboComponentScan(basePackageClasses = {CustomFilterInvocationSecurityMetadataSource.class})
public class BusinessPimsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessPimsApplication.class, args);
    }
}
