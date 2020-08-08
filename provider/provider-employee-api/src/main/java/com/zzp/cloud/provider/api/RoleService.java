package com.zzp.cloud.provider.api;

import com.zzp.cloud.provider.domain.Role;

import java.util.List;

/**
 * 权限服务接口
 * <p>
 *  //TODO
 *  RoleService.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/27 22:21
 * @see  RoleService
 **/
public interface RoleService {

    /**
     * 通过HrId获取权限列表
     * @param hrId HrId
     * @return {@link List<Role>}
     */
    List<Role> getRolesByHrId(Integer hrId);

    /**
     * 获取所有权限
     * @return {@link List<Role>}
     */
    List<Role> getAllRoles();

    /**
     * 添加权限
     * @param role .
     * @return row
     */
    int addRole(Role role);

    /**
     * 通过rid删除权限
     * @param rid .
     * @return .
     */
    int deleteRoleById(Integer rid);
}
