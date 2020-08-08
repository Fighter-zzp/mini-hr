package com.zzp.cloud.business.controller;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.zzp.cloud.business.domain.ChatMsg;
import com.zzp.cloud.common.dto.emp.HrDto;
import com.zzp.cloud.common.utils.JsonUtils;
import com.zzp.cloud.common.utils.OkHttpClientUtil;
import okhttp3.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * //TODO
 * WsController.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/5 23:15
 * @see com.zzp.cloud.business
 **/
@Controller
public class WsController {
    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(ChatMsg chatMsg) throws Exception {
        var hr = getInfo(chatMsg.getToken());
        chatMsg.setFrom(hr.getUsername());
        chatMsg.setFromNickname(hr.getName());
        chatMsg.setDate(new Date());
        simpMessagingTemplate.convertAndSend("/user/queue/chat", chatMsg);
    }

    private HrDto getInfo(String token) throws Exception {
        var params = "?access_token=" + token;
        var instance = OkHttpClientUtil.getInstance();
        var response = instance.getData("http://localhost:9001/auth/info" + params);
        var jsonstr = response.body().string();
        var map = JsonUtils.json2map(jsonstr);
        var hrDto = new HrDto();

        hrDto.setUsername((String) map.get("username"));
        hrDto.setName((String) map.get("name"));
        hrDto.setEnabled((Boolean) map.get("enabled"));
        hrDto.setId((Integer) map.get("id"));
        return hrDto;
    }

}
