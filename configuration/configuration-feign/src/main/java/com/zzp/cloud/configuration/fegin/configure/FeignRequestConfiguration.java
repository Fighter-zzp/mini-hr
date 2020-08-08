package com.zzp.cloud.configuration.fegin.configure;

import com.zzp.cloud.configuration.fegin.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注入feigin拦截器
 * <p>
 *  //TODO
 *  FeignRequestConfiguration.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/1 18:01
 * @see  com.zzp.cloud.configuration.fegin.configure
 **/
@Configuration
public class FeignRequestConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignRequestInterceptor();
    }
}
