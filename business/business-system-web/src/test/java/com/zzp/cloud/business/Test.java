package com.zzp.cloud.business;

import com.zzp.cloud.common.utils.JsonUtils;
import com.zzp.cloud.common.utils.OkHttpClientUtil;
import okhttp3.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Test {
    public static void main(String[] args) throws Exception {
        var formData = new HashMap<String, String>();
        formData.put("token", "9f36f86d-9d87-4843-8b33-d39ed95d3f26");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic ");
        var instance = OkHttpClientUtil.getInstance();
        var response = instance.postData("http://localhost:9001/oauth/check_token", formData);
        var jsonString = Objects.requireNonNull(response.body()).string();
        var stringObjectMap = JsonUtils.json2map(jsonString);
        if (!Boolean.TRUE.equals(stringObjectMap.get("active"))) {
            System.out.println(stringObjectMap.get("active"));
        }
    }
}
