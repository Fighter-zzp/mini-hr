package com.zzp.cloud.provider.api;

import com.zzp.cloud.common.dto.cms.DepartmentDto;
import com.zzp.cloud.provider.domain.Department;

import java.util.List;

/**
 * DepartmentService
 * <p>
 * //TODO
 * DepartmentMapper.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/2 20:57
 * @see com.zzp.cloud.provider.api
 **/
public interface DepartmentService {
    /**
     * 获取所有部门
     *
     * @return
     */
    List<DepartmentDto> getAllDepartments();

    /**
     * 添加
     * 使用存储过程，这将会获取到result并自动填装到dep中
     * 之后可以直接获取dep的这将会获取到result并自动填装到dep中
     * @param dep .
     */
    void addDep(DepartmentDto dep);

    /**
     * 删除
     * 使用存储过程，这将会获取到result并自动填装到dep中
     * 之后可以直接获取dep的这将会获取到result并自动填装到dep中
     * @param dep .
     */
    void deleteDepById(DepartmentDto dep);

    /**
     * 获取不带Children的部门
     *
     * @return .
     */
    List<Department> getAllDepartmentsWithOutChildren();

    List<DepartmentDto> getAllDepartmentDtoWithOutChildren();
}