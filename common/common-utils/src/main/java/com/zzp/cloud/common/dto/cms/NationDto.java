package com.zzp.cloud.common.dto.cms;

import java.io.Serializable;
import java.util.Objects;
/**
 * 族类映射
 * <p>
 *  //TODO
 *  NationDto.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 18:35
 * @see  com.zzp.cloud.common.dto.cms
 **/
public class NationDto implements Serializable {
    private static final long serialVersionUID = 4830283595862855501L;
    /**
     * id
     */
    private Integer id;
    /**
     * 族名（中国）
     */
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var nation = (NationDto) o;
        return Objects.equals(name, nation.name);
    }

    public NationDto() {
    }

    public NationDto(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}