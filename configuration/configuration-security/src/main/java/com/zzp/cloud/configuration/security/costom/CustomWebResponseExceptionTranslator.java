package com.zzp.cloud.configuration.security.costom;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * <p>
 * //TODO
 * CustomWebResponseExceptionTranslator.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/31 19:57
 * @see com.zzp.cloud.configuration.security.costom
 **/
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {
    /**
     * 处理器用于分析Throwable实例。 也可以子来定制其行为
     */
    private final ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();


    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        // 尝试从堆栈跟踪中提取Spring Security Exception
        var causeChain = throwableAnalyzer.determineCauseChain(e);
        // 如果是Class<T>类型的异常则安全返回，不是则返回null
        Exception ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new CustomOauthException(400, "访问异常",((OAuth2Exception)ase).getOAuth2ErrorCode(), ase));
        }
        ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class,
                causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new CustomOauthException(401, "认证异常", e.getMessage(), ase));
        }

        ase = (AccessDeniedException) throwableAnalyzer
                .getFirstThrowableOfType(AccessDeniedException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new CustomOauthException(403, "访问异常", e.getMessage(), e));
        }

        ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer.getFirstThrowableOfType(
                HttpRequestMethodNotSupportedException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new CustomOauthException(405, "请求方式不被允许", e.getMessage(), e));
        }

        return handleOAuth2Exception(new CustomOauthException(500, "服务器错误", e.getMessage(), e));

    }

    /**
     * 处理异常返回
     *
     * @param ase 异常
     * @return
     */
    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception ase) {
        var status = ase.getHttpErrorCode();
        var headers = new HttpHeaders();
        headers.set("Content-Type", "application/json;charset=UTF-8");
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        if (status == HttpStatus.UNAUTHORIZED.value() || (ase instanceof InsufficientScopeException)) {
            headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, ase.getSummary()));
        }
        return ResponseEntity
                .status(HttpStatus.valueOf(status))
                .headers(headers)
                .body(ase);
    }
}
