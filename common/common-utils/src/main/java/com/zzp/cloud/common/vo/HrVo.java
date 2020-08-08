package com.zzp.cloud.common.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * HrDto
 * <p>
 *  //TODO
 *  HrDto.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/30 20:00
 * @see  HrVo
 **/
@Data
public class HrVo implements Serializable {

    private static final long serialVersionUID = 4119366672947363138L;
    /**
     * hrID
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 住宅电话
     */
    private String telephone;

    /**
     * 联系地址
     */
    private String address;

    private Boolean enabled;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private String userface;

    private String remark;

}
