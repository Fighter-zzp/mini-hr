package com.zzp.cloud.business.custom;

import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 自定义认证key生成
 * <p>
 *  //TODO
 *  sss.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/30 17:05
 * @see  com.zzp.cloud.business.config
 **/
public class CustomAuthenticationKeyGenerator extends DefaultAuthenticationKeyGenerator {

    private static final String SCOPE = "scope";

    private static final String USERNAME = "username";

    /**
     * 重写extractKey 完成互踢
     * @param authentication OAuth 2认证令牌
     * @return 生成的key
     */
    @Override
    public String extractKey(OAuth2Authentication authentication) {
        Map<String, String> values = new LinkedHashMap<String, String>();
        OAuth2Request authorizationRequest = authentication.getOAuth2Request();
        if (!authentication.isClientOnly()) {
            values.put(USERNAME, authentication.getName());
        }
        // 去掉extractKey的clientId 条件，不区分终端
        if (authorizationRequest.getScope() != null) {
            values.put(SCOPE, OAuth2Utils.formatParameterList(new TreeSet<String>(authorizationRequest.getScope())));
        }
        return generateKey(values);
    }
}
