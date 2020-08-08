package com.zzp.cloud.common.dto.emp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 权限dto
 * <p>
 *  //TODO
 *  RoleDto.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/5 0:18
 * @see  com.zzp.cloud.common.dto.emp
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Serializable {

    private Integer id;

    private String name;

    /**
     * 角色名称
     */
    private String nameZh;
}
