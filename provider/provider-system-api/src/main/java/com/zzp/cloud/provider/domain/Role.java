package com.zzp.cloud.provider.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

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
public class Role implements Serializable {
    private static final long serialVersionUID = -1386238005342468230L;

    private Integer id;

    private String name;

    /**
     * 角色名称
     */
    private String namezh;

}