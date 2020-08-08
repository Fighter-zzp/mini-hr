package com.zzp.cloud.provider.dto;

import com.zzp.cloud.provider.domain.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单传输类
 * <p>
 *  //TODO
 *  MenuDto.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/27 23:45
 * @see  MenuDto
 **/
@Data
public class MenuDto implements Serializable {

    private static final long serialVersionUID = -2810917002257659898L;

    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconCls;

    private Meta meta;

    private Integer parentId;

    private Boolean enabled;
    private List<MenuDto> children;
    private List<Role> roles;

}
