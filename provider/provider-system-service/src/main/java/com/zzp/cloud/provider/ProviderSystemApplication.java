package com.zzp.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 菜单服务主接口
 * <p>
 * //TODO
 * ProviderMenuApplication.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/27 23:33
 * @see ProviderSystemApplication
 **/
@SpringBootApplication(scanBasePackageClasses = {ProviderSystemApplication.class})
@EnableCaching
@MapperScan(basePackages = "com.zzp.cloud.provider.mapper")
public class ProviderSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderSystemApplication.class, args);
    }

}
