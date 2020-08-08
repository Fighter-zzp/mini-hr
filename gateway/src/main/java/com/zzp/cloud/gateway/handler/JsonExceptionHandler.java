package com.zzp.cloud.gateway.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * web 异常转换为json格式操作
 * <p>
 *  //TODO
 *  JsonExceptionHandler.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/1 14:09
 * @see  com.zzp.cloud.gateway.handler
 **/
public class JsonExceptionHandler implements ErrorWebExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(JsonExceptionHandler.class);

    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();
    private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();
    private List<ViewResolver> viewResolvers = Collections.emptyList();
    private ThreadLocal<Map<Object, Object>> exceptionHandlerResult = new ThreadLocal<>();

    public void setMessageReaders(List<HttpMessageReader<?>> messageReaders) {
        Assert.notNull(messageReaders, "'messageReaders' must not be null");
        this.messageReaders = messageReaders;
    }

    public void setViewResolvers(List<ViewResolver> viewResolvers) {
        this.viewResolvers = viewResolvers;
    }

    public void setMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
        Assert.notNull(messageWriters, "'messageWriters' must not be null");
        this.messageWriters = messageWriters;
    }

    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        var result = exceptionHandlerResult.get();
        return ServerResponse.status((HttpStatus) result.get("httpStatus"))
                .contentType(new MediaType("application", "json", StandardCharsets.UTF_8))
                .body(BodyInserters.fromValue(result.get("body")));
    }

    private Mono<? extends Void> write(ServerWebExchange exchange, ServerResponse response) {
        exchange.getResponse().getHeaders().setContentType(response.headers().getContentType());
        return response.writeTo(exchange, new ResponseContext());
    }

    private class ResponseContext implements ServerResponse.Context {
        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return JsonExceptionHandler.this.messageWriters;
        }

        @Override
        public List<ViewResolver> viewResolvers() {
            return JsonExceptionHandler.this.viewResolvers;
        }
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        // 按照异常类型进行处理
        HttpStatus httpStatus;
        String body;
        if (throwable instanceof NotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
            body = "Service Not Found";
        } else if (throwable instanceof ResponseStatusException) {
            var responseStatusException = (ResponseStatusException) throwable;
            httpStatus = responseStatusException.getStatus();
            body = responseStatusException.getMessage();
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = "Internal Server Error";
        }

        // 封装响应结果
        var result = new HashMap<>(2, 1);
        result.put("httpStatus", httpStatus);

        String msg = "{\"code\":" + httpStatus.value() + ",\"message\": \"" + body + "\"}";
        result.put("body", msg);

        // 错误日志
        ServerHttpRequest request = serverWebExchange.getRequest();
        log.error("[全局系统异常]\r\n请求路径：{}\r\n异常记录：{}", request.getPath(), throwable.getMessage());

        if (serverWebExchange.getResponse().isCommitted()) {
            return Mono.error(throwable);
        }
        exceptionHandlerResult.set(result);
        ServerRequest newRequest = ServerRequest.create(serverWebExchange, this.messageReaders);
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse).route(newRequest)
                .switchIfEmpty(Mono.error(throwable))
                .flatMap((handler) -> handler.handle(newRequest))
                .flatMap((response) -> write(serverWebExchange, response));
    }
}
