package com.zzp.cloud.configuration.fegin.interceptor;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * feign拦截器
 * <p>
 *  feign拦截器，用来把access_token封装到请求里
 *  FeignRequestInterceptor.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/1 18:01
 * @see  com.zzp.cloud.configuration.fegin.interceptor
 **/
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 获取请求属性
        var requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        // 得到http请求对象
        var req = requestAttributes.getRequest();

        // 设置请求头
        var headerNames = req.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                var name = headerNames.nextElement();
                var value = req.getHeader(name);
                requestTemplate.header(name, value);
            }
        }
        // 设置请求体，这里主要是为了传递 access_token
        var parameterNames = req.getParameterNames();
        // 设置请求体
        var body = new StringBuilder();
        if (parameterNames!=null){
            while (parameterNames.hasMoreElements()){
                var name = parameterNames.nextElement();
                var value = req.getParameter(name);
                // 将token加入请求头
                if ("access_token".equals(name)){
                    requestTemplate.header("authorization", "Bearer " + value);
                }
                // 其他参数存入请求体
                else {
                    body.append(name).append("=").append(value).append("&");
                }
            }
        }
        // 设置请求体
        if (body.length() > 0) {
            // 去掉最后一位 & 符号
            body.deleteCharAt(body.length() - 1);
            // 存入请求体  Charset.defaultCharset()字符集设置
            requestTemplate.body(Request.Body.create(body.toString(), Charset.defaultCharset()));
        }
    }
}
