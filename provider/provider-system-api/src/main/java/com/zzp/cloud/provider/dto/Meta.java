package com.zzp.cloud.provider.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 媒体类
 * <p>
 *  //TODO
 *  Meta.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/27 23:48
 * @see  Meta
 **/
@Data
public class Meta implements Serializable {
    private static final long serialVersionUID = 6602351009352052258L;
    /**
     * 保持在线
     */
    private Boolean keepAlive;
    /**
     * 请求认证
     */
    private Boolean requireAuth;
}
