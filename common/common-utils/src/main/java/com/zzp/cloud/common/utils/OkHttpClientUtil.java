package com.zzp.cloud.common.utils;

import okhttp3.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author 佐斯特勒
 * @Description 封装OkHttp 达到发送请求功能
 * @Date 2020/1/4
 * @Time 0:53
 * @ClassName OkHttpClientUtil
 **/
public class OkHttpClientUtil {
    /**
     * 超时时间
     */
    private static final int READ_TIMEOUT = 100;
    private static final int CONNECT_TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 60;
    /**
     * json格式
     */
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    /**
     * 流存储
     */
    private static final byte[] LOCKER = new byte[0];
    /**
     * 本身单例
     */
    private static OkHttpClientUtil mInstance;
    /**
     * okHttp客户端
     */
    private OkHttpClient okHttpClient;

    private OkHttpClientUtil() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        // 读取超时
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        // 连接超时
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        //写入超时
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient = clientBuilder.build();
    }

    /**
     * 单例模式获取 NetUtils
     *
     * @return {@link OkHttpClientUtil}
     */
    public static OkHttpClientUtil getInstance() {
        if (mInstance == null) {
            synchronized (LOCKER) {
                if (mInstance == null) {
                    mInstance = new OkHttpClientUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * GET，同步方式，获取网络数据
     *
     * @param url 请求地址
     * @return {@link Response}
     */
    public Response getData(String url) {
        // 构造 Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        // 将 Request 封装为 Call
        Call call = okHttpClient.newCall(request);
        // 执行 Call，得到 Response
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 发送带请求头的post数据
     * @param url 地址
     * @param bodyParams 请求体
     * @param headers 请求头（复审）
     * @return {@link Response}
     */
    public Response postData(String url, Map<String, String> bodyParams, Headers headers){
        // 构造 RequestBody
        var body = setRequestBody(bodyParams);
        // 构造 Request
        Request.Builder requestBuilder = new Request.Builder();
        var request = requestBuilder.post(body).url(url).headers(headers).build();
        // 将 Request 封装为 Call
        var call = okHttpClient.newCall(request);
        // 执行 Call，得到 Response
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * POST 请求，同步方式，提交数据
     *
     * @param url        请求地址
     * @param bodyParams 请求参数
     * @return {@link Response}
     */
    public Response postData(String url, Map<String, String> bodyParams) {
        // 构造 RequestBody
        RequestBody body = setRequestBody(bodyParams);
        // 构造 Request
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.post(body).url(url).build();
        // 将 Request 封装为 Call
        Call call = okHttpClient.newCall(request);
        // 执行 Call，得到 Response
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * GET 请求，异步方式，获取网络数据
     *
     * @param url       请求地址
     * @param myNetCall 回调函数
     */
    public void getDataAsync(String url, final MyNetCall myNetCall) {
        // 构造 Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        // 将 Request 封装为 Call
        Call call = okHttpClient.newCall(request);
        // 执行 Call
        call.enqueue(getCallback(myNetCall));
    }

    /**
     * POST 请求，异步方式，提交数据
     *
     * @param url        请求地址
     * @param bodyParams 请求参数
     * @param myNetCall  回调函数
     */
    public void postDataAsync(String url, Map<String, String> bodyParams, final MyNetCall myNetCall) {
        // 构造 RequestBody
        RequestBody body = setRequestBody(bodyParams);
        // 构造 Request
        buildRequest(url, myNetCall, body);
    }

    /**
     * 同步 POST 请求，使用 JSON 格式作为参数
     *
     * @param url  请求地址
     * @param json JSON 格式参数
     * @return 响应结果
     * @throws IOException 异常
     */
    public String postJson(String url, String json) throws IOException {
        var body = RequestBody.create(JSON, json);
        var request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        var response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return Objects.requireNonNull(response.body()).string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 异步 POST 请求，使用 JSON 格式作为参数
     *
     * @param url       请求地址
     * @param json      JSON 格式参数
     * @param myNetCall 回调函数
     * @throws IOException 异常
     */
    public void postJsonAsync(String url, String json, final MyNetCall myNetCall) throws IOException {
        var body = RequestBody.create(JSON,json);
        // 构造 Request
        buildRequest(url, myNetCall, body);
    }

    /**
     * 构造 POST 请求参数
     *
     * @param bodyParams 请求参数
     * @return {@link RequestBody}
     */
    private RequestBody setRequestBody(Map<String, String> bodyParams) {
        RequestBody body;
        okhttp3.FormBody.Builder formEncodingBuilder = new okhttp3.FormBody.Builder();
        if (bodyParams != null) {
            Iterator<String> iterator = bodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formEncodingBuilder.add(key, bodyParams.get(key));
            }
        }
        body = formEncodingBuilder.build();
        return body;
    }

    /**
     * 构造 Request 发起异步请求
     *
     * @param url       请求地址
     * @param myNetCall 回调函数
     * @param body      {@link RequestBody}
     */
    private void buildRequest(String url, MyNetCall myNetCall, RequestBody body) {
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.post(body).url(url).build();
        // 将 Request 封装为 Call
        Call call = okHttpClient.newCall(request);
        // 执行 Call
        call.enqueue(getCallback(myNetCall));
    }

    /**
     * 异步回调函数
     * @param myNetCall 自定义接口
     * @return Callback
     */
    private Callback getCallback(MyNetCall myNetCall){
        return new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                myNetCall.failed(call, e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                myNetCall.success(call, response);
            }
        };
    }

    /**
     * 自定义网络回调接口
     */
    public interface MyNetCall {
        /**
         * 请求成功的回调处理
         *
         * @param call     {@link Call}
         * @param response {@link Response}
         * @throws IOException 异常
         */
        void success(Call call, Response response) throws IOException;

        /**
         * 请求失败的回调处理
         *
         * @param call {@link Call}
         * @param e    异常
         */
        void failed(Call call, IOException e);
    }
}
