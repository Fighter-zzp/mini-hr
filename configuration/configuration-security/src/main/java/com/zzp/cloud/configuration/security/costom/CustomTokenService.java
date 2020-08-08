package com.zzp.cloud.configuration.security.costom;

import com.google.common.collect.Maps;
import com.zzp.cloud.common.utils.JsonUtils;
import com.zzp.cloud.common.utils.OkHttpClientUtil;
import okhttp3.Headers;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomTokenService extends RemoteTokenServices {

    protected final Log logger = LogFactory.getLog(getClass());

    private OkHttpClientUtil okHttpClientUtil;

    private String checkTokenEndpointUrl;

    private String clientId;

    private String clientSecret;

    private String tokenName = "token";

    private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();

    public CustomTokenService() {
        okHttpClientUtil = OkHttpClientUtil.getInstance();
    }

    @Override
    public void setCheckTokenEndpointUrl(String checkTokenEndpointUrl) {
        this.checkTokenEndpointUrl = checkTokenEndpointUrl;
    }

    @Override
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public void setTokenConverter(AccessTokenConverter tokenConverter) {
        this.tokenConverter = tokenConverter;
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        return super.readAccessToken(accessToken);
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        var formData = new HashMap<String, String>(16);
        formData.put(tokenName, accessToken);
        var headers = Headers.of("Authorization", getAuthorizationHeader(clientId, clientSecret));
        Map<String, Object> map;
        try {

            var response = okHttpClientUtil.postData(checkTokenEndpointUrl,formData,headers);
            var body = Objects.requireNonNull(response.body(),"无法获取认证服务器数据！");
            map = JsonUtils.json2map(body.string());
        }catch (Exception e){
            map = Maps.newHashMap();
            map.put("error", "无法从认证服务器验证token！");
        }

        if (map.containsKey("error")) {
            if (logger.isDebugEnabled()) {
                logger.debug("check_token returned error: " + map.get("error"));
            }
            throw new InvalidTokenException(accessToken);
        }
        var active = map.get("active");
        // gh-838
        if (!Boolean.TRUE.equals(active)) {
            logger.debug("check_token returned active attribute: " + active);
            throw new InvalidTokenException(accessToken);
        }

        return tokenConverter.extractAuthentication(map);
    }

    private String getAuthorizationHeader(String clientId, String clientSecret) {

        if(clientId == null || clientSecret == null) {
            logger.warn("Null Client ID or Client Secret detected. Endpoint that requires authentication will reject request with 401 error.");
        }

        String creds = String.format("%s:%s", clientId, clientSecret);
        return "Basic " + new String(Base64.encode(creds.getBytes(StandardCharsets.UTF_8)));
    }

}
