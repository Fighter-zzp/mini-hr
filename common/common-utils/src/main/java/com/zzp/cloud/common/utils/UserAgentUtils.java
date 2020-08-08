package com.zzp.cloud.common.utils;

import com.zzp.cloud.common.dto.IpInfo;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 佐斯特勒
 * <p>
 * 用户代理工具类
 * </p>
 * @version v1.0.0
 * @date 2020/2/27 14:37
 * @see UserAgentUtils
 **/
public class UserAgentUtils {

    /**
     * 获取用户代理对象（请请求头中）
     *
     * @param req 请求{@link HttpServletRequest}
     * @return 代理对象 {@link UserAgent}
     */
    public static UserAgent getUserAgent(HttpServletRequest req) {
        return UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
    }

    /**
     * 获取用户浏览器
     *
     * @param req 请求{@link HttpServletRequest}
     * @return 浏览器对象 {@link Browser}
     */
    public static Browser getBrowser(HttpServletRequest req) {
        return getUserAgent(req).getBrowser();
    }

    /**
     * 获取用户的 IP 地址
     *
     * @param req {@link HttpServletRequest}
     * @return {@code String} 用户 IP 地址
     */
    public static String getIpAddr(HttpServletRequest req) {
        // 获取表示 HTTP 请求端真实的IP
        /*
         * 层层递进一步一步获取最终的地址
         */
        var ip = req.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        if (ip.split(",").length > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    /**
     * 通过 IP 获取地址 (淘宝接口)
     *
     * @param ip {@code String} 用户 IP 地址
     * @return {@code String} 用户地址
     */
    public static IpInfo getIpInfo(String ip) {
        if ("localhost".equals(ip)) {
            ip = "127.0.0.1";
        }

        try {
            var url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            var connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            // 获取流
            var in = connection.getInputStream();
            var bufferedReader = new BufferedReader(new InputStreamReader(in));
            var temp = new StringBuilder();
            var line = bufferedReader.readLine();
            while (line != null) {
                temp.append(line).append("/r/n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            return JsonUtils.json2pojoByTree(temp.toString(), "data", IpInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new IpInfo();
    }
}
