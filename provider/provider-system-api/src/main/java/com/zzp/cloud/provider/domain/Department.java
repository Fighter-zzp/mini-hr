package com.zzp.cloud.provider.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 部门
 * <p>
 *  //TODO
 *  Department.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 21:00
 * @see  com.zzp.cloud.provider.domain
 **/
@Data
@Table(name = "vhr.department")
public class Department implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 部门名称
     */
    @Column(name = "`name`")
    private String name;

    @Column(name = "parentId")
    private Integer parentid;

    @Column(name = "depPath")
    private String deppath;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "isParent")
    private Boolean isparent;

    private static final long serialVersionUID = 1L;
}