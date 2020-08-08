package com.zzp.cloud.common.dto.cms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * 部门传输类
 * <p>
 *  //TODO
 *  Department.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 18:37
 * @see  com.zzp.cloud.common.dto.cms
 **/
public class DepartmentDto implements Serializable {
    private static final long serialVersionUID = -3666563114838870732L;
    private Integer id;

    private String name;

    private Integer parentId;

    private String depPath;

    private Boolean enabled;

    private Boolean isParent;

    private List<DepartmentDto> children = new ArrayList<>();

    /**
     * 从存储过程中获取到result
     * -2 部门下有子部门
     * -1 部门下有员工
     * 1 操作成功
     */
    private Integer result;

    public DepartmentDto() {
    }

    public DepartmentDto(String name) {

        this.name = name;
    }

    /**
     * 比较部门名
     * @param o 部门对象
     * @return .
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var that = (DepartmentDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<DepartmentDto> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentDto> children) {
        this.children = children;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDepPath() {
        return depPath;
    }

    public void setDepPath(String depPath) {
        this.depPath = depPath;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }
}