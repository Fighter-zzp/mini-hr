package com.zzp.cloud.business;

import com.zzp.cloud.configuration.security.config.ResourceServerConfiguration;
import com.zzp.cloud.configuration.security.costom.CustomFilterInvocationSecurityMetadataSource;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 系统服务主机款
 * <p>
 * //TODO
 * BusinessSystemApplication.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/28 14:22
 * @see com.zzp.cloud.business
 **/
@SpringBootApplication(
        scanBasePackageClasses = {BusinessSystemApplication.class}
        , scanBasePackages = {"com.zzp.cloud.configuration.security"})
@EnableDiscoveryClient
@EnableFeignClients
@DubboComponentScan(basePackageClasses = {CustomFilterInvocationSecurityMetadataSource.class})
public class BusinessSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessSystemApplication.class, args);
    }
}
