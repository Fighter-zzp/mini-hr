package com.zzp.cloud.business.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzp.cloud.configuration.security.domain.HrDetails;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 登录过滤器
 * <p>
 *  //TODO
 *  LoginFilter.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/28 12:34
 * @see  LoginFilter
 **/
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    @Resource
    private SessionRegistry sessionRegistry;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "认证方法不支持: " + request.getMethod());
        }
        // 认证码
        var verifyCode = (String) request.getSession().getAttribute("verify_code");
        if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE) ||
                request.getContentType().contains("application/json;charset=utf-8")) {
            Map<String,String> loginData = new HashMap<>(16);
            try (var is = request.getInputStream()){
                loginData = new ObjectMapper().readValue(is, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                var code = loginData.get("code");
                checkCode(response, code, verifyCode);
            }

            var username = loginData.get(getUsernameParameter());
            var password = loginData.get(getPasswordParameter());
            username = Optional.ofNullable(username).orElse("").trim();
            password = Optional.ofNullable(password).orElse("");
            // 因为request.getInputStream() 在请求里只可使用一次，所以把原生密码输入一次请求中
            request.setAttribute("pw",password);
            // 认证token
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request,usernamePasswordAuthenticationToken);
            return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
        } else {
            checkCode(response, request.getParameter("code"), verifyCode);
            return super.attemptAuthentication(request, response);
        }
    }

    private void checkCode(HttpServletResponse response, String code, String verifyCode) {
        if (code == null || verifyCode == null || "".equals(code) || !verifyCode.equalsIgnoreCase(code.toLowerCase())) {
            //验证码不正确
//             throw new AuthenticationServiceException("验证码不正确");
        }
    }
}
