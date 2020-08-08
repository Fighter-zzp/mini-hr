package com.zzp.cloud.configuration.security.costom;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 自定义异常端点处理
 * <p>
 * //TODO
 * CustomAuthenticationEntryPoint.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/1 0:45
 * @see com.zzp.cloud.configuration.security.costom
 **/
@Component
public class CustomAuthenticationEntryPoint extends AbstractOAuth2SecurityExceptionHandler implements AuthenticationEntryPoint {

    private WebResponseExceptionTranslator<?> exceptionTranslator = new DefaultWebResponseExceptionTranslator();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException, IOException {
        optHandler(request,response,authException);
    }

    private void optHandler(HttpServletRequest request, HttpServletResponse response, Exception authException){
        try {
            ResponseEntity<?> resp = exceptionTranslator.translate(authException);
            response.setStatus(resp.getStatusCode().value());
            resp.getHeaders().toSingleValueMap().forEach(response::addHeader);
            new ObjectMapper().writeValue(response.getOutputStream(),resp.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setExceptionTranslator(WebResponseExceptionTranslator<?> exceptionTranslator) {
        super.setExceptionTranslator(null);
        this.exceptionTranslator = exceptionTranslator;
    }
}

