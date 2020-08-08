package com.zzp.cloud.provider.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
/**
 * 等级职位
 * <p>
 *  //TODO
 *  Joblevel.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/4 20:52
 * @see  com.zzp.cloud.provider.domain
 **/
@Data
@Table(name = "vhr.joblevel")
public class Joblevel implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 职称名称
     */
    @Column(name = "`name`")
    private String name;

    @Column(name = "titleLevel")
    private Object titlelevel;

    @Column(name = "createDate")
    private Date createdate;

    @Column(name = "enabled")
    private Boolean enabled;

    private static final long serialVersionUID = 1L;
}