package com.zzp.cloud.provider.mapper;

import com.zzp.cloud.common.dto.cms.DepartmentDto;
import com.zzp.cloud.provider.domain.Department;
import tk.mybatis.mapper.CommonMapper;

import java.util.List;

/**
 * DepartmentMapper
 * <p>
 *  //TODO
 *  DepartmentMapper.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 20:57
 * @see  com.zzp.cloud.provider.mapper
 **/
public interface DepartmentMapper extends CommonMapper<Department> {
    /**
     * 通过父id找全部部门
     * @param pid
     * @return
     */
    List<DepartmentDto> getAllDepartmentsByParentId(Integer pid);

    /**
     * 使用存储过程保存 部门
     * @param dep .
     */
    void addDep(DepartmentDto dep);

    /**
     * 使用存储过程删除 部门
     * @param dep .
     */
    void deleteDepById(DepartmentDto dep);

}