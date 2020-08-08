package com.zzp.cloud.business.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.zzp.cloud.business.filter.LoginFilter;
import com.zzp.cloud.business.service.UserDetailsServiceImpl;
import com.zzp.cloud.common.dto.RespBean;
import com.zzp.cloud.common.utils.JsonUtils;
import com.zzp.cloud.common.utils.OkHttpClientUtil;
import com.zzp.cloud.configuration.security.domain.HrDetails;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * web安全配置
 * <p>
 * //TODO
 * WebSecurityConfiguration.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/28 0:38
 * @see com.zzp.cloud.business.config
 **/
@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 密码编码器
     *
     * @return {@link BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 用于支持密码模式
     *
     * @return .
     * @throws Exception .
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 设计用户详情服务
     *
     * @param auth 认证管理构造器
     * @throws Exception .
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * 忽略一些静态资源
     *
     * @param web .
     * @throws Exception .
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/auth/doLogin", "/auth/verifyCode","/auth/logout");
    }

    @Bean
    public LoginFilter loginFilter() {
        var loginFilter = new LoginFilter();
        // 认证成功操作
        loginFilter.setAuthenticationSuccessHandler((request, response, auth) -> {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            Map<String,String> loginData = new HashMap<>(16);
            var out = response.getWriter();
            var hr = (HrDetails) auth.getPrincipal();
            // 获取用户输入的密码
            var password = Optional
                    .ofNullable(request.getParameter("password"))
                    .orElseGet(()-> {
                        var pw = request.getAttribute("pw");
                        request.removeAttribute("pw");
                        return (String)pw ;
                    });
            var token = getToken(hr.getUsername(), password);
            hr.setToken(token);
            // 避免暴露密码
            hr.setPassword(null);
            var ok = RespBean.ok("登录成功!", hr);
            String s = "";
            try {
                s = JsonUtils.obj2json(ok);
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.write(s);
            out.flush();
            out.close();
        });

        // 认证失败操作
        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    var out = response.getWriter();
                    var respBean = RespBean.error(exception.getMessage());
                    if (exception instanceof LockedException) {
                        respBean.setMsg("账户被锁定，请联系管理员!");
                    } else if (exception instanceof CredentialsExpiredException) {
                        respBean.setMsg("密码过期，请联系管理员!");
                    } else if (exception instanceof AccountExpiredException) {
                        respBean.setMsg("账户过期，请联系管理员!");
                    } else if (exception instanceof DisabledException) {
                        respBean.setMsg("账户被禁用，请联系管理员!");
                    } else if (exception instanceof BadCredentialsException) {
                        respBean.setMsg("用户名或者密码输入错误，请重新输入!");
                    }
                    try {
                        out.write(JsonUtils.obj2json(respBean));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    out.flush();
                    out.close();
                }
        );

        try {
            // 设置认证管理器
            loginFilter.setAuthenticationManager(authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginFilter.setFilterProcessesUrl("/auth/doLogin");
        var sessionStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        sessionStrategy.setMaximumSessions(1);
        loginFilter.setSessionAuthenticationStrategy(sessionStrategy);
        return loginFilter;
    }

    /**
     * 会话登录实现
     *
     * @return {@link SessionRegistryImpl}
     */
    @Bean
    public SessionRegistryImpl sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/**")
                .permitAll()
                .and()
                .sessionManagement()
                // 无状态 这里不使用session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable().exceptionHandling();

        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 获取token
     *
     * @param username 用户名
     * @param password 密码
     * @return token字符串
     */
    private String getToken(String username, String password) {
        // 参数
        var oauth2GrantType = "password";
        var oauth2ClientId = "client";
        var oauth2ClientSecret = "secret";

        var instance = OkHttpClientUtil.getInstance();
        // 通过 HTTP 客户端请求登录接口
        Map<String, String> params = Maps.newHashMap();
        params.put("username", username);
        params.put("password", password);
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);
        var url = "http://localhost:9001/oauth/token";
        try {
            var response = instance.postData(url, params);
            var jsonString = Objects.requireNonNull(response.body()).string();
            var stringObjectMap = JsonUtils.json2map(jsonString);
            return (String) stringObjectMap.get("access_token");
            // 发送登录日志
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "异常 *---* token获取异常!";
    }
}
