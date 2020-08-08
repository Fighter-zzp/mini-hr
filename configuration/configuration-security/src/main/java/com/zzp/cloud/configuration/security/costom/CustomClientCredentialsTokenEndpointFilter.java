package com.zzp.cloud.configuration.security.costom;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义客户端过滤和验证端点的令牌的OAuth2端点
 * <p>
 *  过滤和验证端点的令牌的OAuth2端点。
 *  CustomClientCredentialsTokenEndpointFilter.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/1 1:19
 * @see  com.zzp.cloud.configuration.security.costom
 **/
public class CustomClientCredentialsTokenEndpointFilter extends ClientCredentialsTokenEndpointFilter {
    private AuthenticationEntryPoint authenticationEntryPoint;
    private AuthorizationServerSecurityConfigurer authorizationServerConfigurer;

    public CustomClientCredentialsTokenEndpointFilter(AuthorizationServerSecurityConfigurer authorizationServerConfigurer){
        this.authorizationServerConfigurer = authorizationServerConfigurer;
    }

    public CustomClientCredentialsTokenEndpointFilter() {
        super();
    }

    public CustomClientCredentialsTokenEndpointFilter(String path) {
        super(path);
    }

    @Override
    public void setAllowOnlyPost(boolean allowOnlyPost) {
        super.setAllowOnlyPost(allowOnlyPost);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return super.attemptAuthentication(request, response);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        // 干掉父类的
        super.setAuthenticationEntryPoint(null);
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected AuthenticationManager getAuthenticationManager() {
        return this.authorizationServerConfigurer.and().getSharedObject(AuthenticationManager.class);
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }
}
