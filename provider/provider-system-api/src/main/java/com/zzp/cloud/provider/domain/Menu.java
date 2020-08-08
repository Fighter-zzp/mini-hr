package com.zzp.cloud.provider.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
/**
 * 菜单实体类
 * <p>
 *  //TODO
 *  Menu.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/27 23:37
 * @see  Menu
 **/
@Data
@Table(name = "vhr.menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = 7696039283011206776L;
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "`path`")
    private String path;

    @Column(name = "component")
    private String component;

    @Column(name = "`name`")
    private String name;

    @Column(name = "iconCls")
    private String iconcls;

    @Column(name = "keepAlive")
    private Boolean keepalive;

    @Column(name = "requireAuth")
    private Boolean requireauth;

    @Column(name = "parentId")
    private Integer parentid;

    @Column(name = "enabled")
    private Boolean enabled;

}