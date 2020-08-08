package com.zzp.cloud.common.dto.cms;

import java.io.Serializable;
import java.util.Objects;

/**
 * 政治状态
 * <p>
 *  比较的是转态名
 *  Politicsstatus.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 18:38
 * @see  com.zzp.cloud.common.dto.cms
 **/
public class PoliticsStatusDto implements Serializable {
    private Integer id;
    /**
     * 政治状态名
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
        var that = (PoliticsStatusDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    public PoliticsStatusDto() {

    }

    public PoliticsStatusDto(String name) {

        this.name = name;
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