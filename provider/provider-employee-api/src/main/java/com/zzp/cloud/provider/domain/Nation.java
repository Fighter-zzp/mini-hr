package com.zzp.cloud.provider.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 族
 * <p>
 *  //TODO
 *  Nation.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/3 22:30
 * @see  com.zzp.cloud.provider.domain
 **/
@Data
@Table(name = "vhr.nation")
public class Nation implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "`name`")
    private String name;

    private static final long serialVersionUID = 1L;
}