package com.zzp.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Hr 服务主接口程序
 * <p>
 * //TODO
 * ProviderEmployeeApplication.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/27 19:29
 * @see ProviderEmployeeApplication
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.zzp.cloud.provider.mapper")
public class ProviderEmployeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderEmployeeApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
