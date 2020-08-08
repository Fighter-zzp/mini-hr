package com.zzp.cloud.business.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzp.cloud.business.custom.CustomAuthenticationKeyGenerator;
import com.zzp.cloud.business.custom.CustomTokenServices;
import com.zzp.cloud.business.service.UserDetailsServiceImpl;
import com.zzp.cloud.common.dto.RespBean;
import com.zzp.cloud.configuration.security.costom.CustomAuthenticationEntryPoint;
import com.zzp.cloud.configuration.security.costom.CustomClientCredentialsTokenEndpointFilter;
import com.zzp.cloud.configuration.security.costom.CustomOauthException;
import com.zzp.cloud.configuration.security.costom.CustomWebResponseExceptionTranslator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author 佐斯特勒
 * <p>
 * 人皆知有用之用，而莫知无用之用也
 * </p>
 * @version v1.0.0
 * @date 2020/1/3 16:56
 * @see AuthorizationServerConfiguration
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    /**
     * 用户详情服务
     */
    @Resource
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 用于密码模式
     */
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 用于连接redis
     */
    @Resource
    private RedisConnectionFactory redisConnectionFactory;


    /**
     * 配置数据源
     * 指定数据源
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 增加TokenStore配置
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        // 使用redis存储令牌
        var tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setAuthenticationKeyGenerator(new CustomAuthenticationKeyGenerator());
        return tokenStore;
    }

    /**
     * 使用jdbc配置认证信息
     *
     * @return
     */
    @Bean
    public JdbcClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource());
    }

    /**
     * 使用密码模式
     *
     * @param endpoints .
     * @throws Exception .
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenServices(tokenService(endpoints))
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore());
    }

    private CustomTokenServices tokenService(AuthorizationServerEndpointsConfigurer endpoints) {
        var tokenServices = new CustomTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        addUserDetailsService(tokenServices, userDetailsService);
        return tokenServices;
    }

    private void addUserDetailsService(CustomTokenServices tokenServices, UserDetailsService userDetailsService) {
        if (userDetailsService != null) {
            var provider = new PreAuthenticatedAuthenticationProvider();
            provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(
                    userDetailsService));
            tokenServices.setAuthenticationManager(new ProviderManager(Collections.singletonList(provider)));
        }
    }

    /**
     * 安全认证
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 允许客户端访问 /oauth/check_token 检查 token
                .tokenKeyAccess("permitAll()")
                // 要访问/oauth/check_token必须设置为permitAll()，但这样所有人都可以访问了，设为isAuthenticated()又导致访问不了，这个问题暂时没找到解决方案
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 配置客户端
     *
     * @param clients ${@link ClientDetailsServiceConfigurer}
     * @throws Exception 客户端异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }
}
