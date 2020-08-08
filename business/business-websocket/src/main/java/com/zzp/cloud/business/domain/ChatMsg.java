package com.zzp.cloud.business.domain;

import lombok.Data;

import java.util.Date;
/**
 * 聊天消息
 * <p>
 *  //TODO
 *  ChatMsg.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/5 23:13
 * @see  com.zzp.cloud.business.domain
 **/
@Data
public class ChatMsg {
    private String from;
    private String to;
    private String content;
    private Date date;
    private String fromNickname;
    private String token;
}
