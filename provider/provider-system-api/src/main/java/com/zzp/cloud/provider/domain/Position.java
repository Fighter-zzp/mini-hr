package com.zzp.cloud.provider.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
/**
 * 职位
 * <p>
 *  //TODO
 *  Position.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 23:06
 * @see  com.zzp.cloud.provider.domain
 **/
@Data
@Table(name = "vhr.`position`")
public class Position implements Serializable {
    private static final long serialVersionUID = 162743224653051990L;
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 职位
     */
    @Column(name = "`name`")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Column(name = "createDate")
    private Date createdate;

    @Column(name = "enabled")
    private Boolean enabled;

}