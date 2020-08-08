package com.zzp.cloud.configuration.security.costom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.SimpleTimeZone;

/**
 * 自定义异常序列化
 * <p>
 * //TODO
 * CustomOauthExceptionSerializer.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/31 19:51
 * @see com.zzp.cloud.configuration.security.costom
 **/

public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {
    private final Log log = LogFactory.getLog(CustomOauthExceptionSerializer.class);

    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        var request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();

        gen.writeStartObject();
        gen.writeStringField("status", String.valueOf(value.getHttpErrorCode()));
        gen.writeStringField("error", value.getOAuth2ErrorCode());
        gen.writeStringField("message", value.getMessage());
        gen.writeStringField("path", request.getServletPath());
        gen.writeStringField("timestamp", LocalDateTime.now().toString());
        if (value.getAdditionalInformation() != null) {
            value.getAdditionalInformation().forEach((k, v) -> {
                try {
                    gen.writeStringField(k, v);
                } catch (IOException e) {
                    log.error("authException的额外消息添加失败");
                }
            });
        }
        gen.writeEndObject();
    }
}
