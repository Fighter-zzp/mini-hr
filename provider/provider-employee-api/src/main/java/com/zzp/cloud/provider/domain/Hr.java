package com.zzp.cloud.provider.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * Hr实体类
 * <p>
 *  //TODO
 *  Hr.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/27 20:00
 * @see  Hr
 **/
@Data
@Table(name = "vhr.hr")
public class Hr implements Serializable {

    private static final long serialVersionUID = -1986393370685150496L;
    /**
     * hrID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 姓名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 手机号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 住宅电话
     */
    @Column(name = "telephone")
    private String telephone;

    /**
     * 联系地址
     */
    @Column(name = "address")
    private String address;

    @Column(name = "enabled")
    private Boolean enabled;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    @Column(name = "userface")
    private String userface;

    @Column(name = "remark")
    private String remark;


}