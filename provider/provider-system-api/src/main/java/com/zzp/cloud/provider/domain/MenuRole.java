package com.zzp.cloud.provider.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
/**
 * 菜单与权限的对应类
 * <p>
 *  //TODO
 *  MenuRole.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/4 23:16
 * @see  com.zzp.cloud.provider.domain
 **/
@Data
@Table(name = "vhr.menu_role")
public class MenuRole implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "mid")
    private Integer mid;

    @Column(name = "rid")
    private Integer rid;

    private static final long serialVersionUID = 1L;
}