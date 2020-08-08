package com.zzp.cloud.provider.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限实体类
 * <p>
 *  //TODO
 *  Role.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/27 22:21
 * @see  Role
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vhr.`role`")
public class Role implements Serializable {
    private static final long serialVersionUID = -1386238005342468230L;
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "`name`")
    private String name;

    /**
     * 角色名称
     */
    @Column(name = "nameZh")
    private String namezh;

}