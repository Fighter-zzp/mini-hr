package com.zzp.cloud.configuration.security.costom;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义Ouath异常
 * <p>
 * //TODO
 * CustomOauthException.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/31 19:43
 * @see com.zzp.cloud.configuration.security.costom
 **/
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {

    private int httpErrorCode;
    private String auth2ErrorCode;

    public CustomOauthException(String msg) {
        super(msg);
    }

    public CustomOauthException(int httpErrorCode, String auth2ErrorCode, String msg, Throwable t) {
        super(msg, t);
        this.httpErrorCode = httpErrorCode;
        this.auth2ErrorCode = auth2ErrorCode;
    }

    @Override
    public int getHttpErrorCode() {
        return this.httpErrorCode;
    }

    @Override
    public String getOAuth2ErrorCode() {
        return this.auth2ErrorCode;
    }
}
