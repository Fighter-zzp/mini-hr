package com.zzp.cloud.configuration.security.config;

import com.zzp.cloud.configuration.security.costom.*;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

/**
 * <p>
 * //TODO
 * ProfileResourceServerConfiguration.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/30 20:23
 * @see com.zzp.cloud.business.config
 **/

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Resource
    private CustomFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    @Resource
    private CustomUrlDecisionManager urlDecisionManager;

    @Resource
    private CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * web端异常信息回复
     */
    @Bean
    public CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        // 权限获取自定义配置
                        fsi.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
                        fsi.setAccessDecisionManager(urlDecisionManager);
                        return fsi;
                    }
                });
    }

    @Resource
    private ResourceServerProperties resourceServerProperties;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        var tokenService = new CustomTokenService();
        tokenService.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
        tokenService.setClientId(resourceServerProperties.getClientId());
        tokenService.setClientSecret(resourceServerProperties.getClientSecret());
        authenticationEntryPoint.setExceptionTranslator(customWebResponseExceptionTranslator());
        resources.authenticationEntryPoint(authenticationEntryPoint);
        resources.resourceId("backend-resources").tokenServices(tokenService);
    }
}