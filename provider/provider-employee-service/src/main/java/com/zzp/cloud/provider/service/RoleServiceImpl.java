package com.zzp.cloud.provider.service;

import com.zzp.cloud.provider.api.RoleService;
import com.zzp.cloud.provider.domain.Role;
import com.zzp.cloud.provider.mapper.HrRoleMapper;
import com.zzp.cloud.provider.mapper.RoleMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限服务实现
 * <p>
 * //TODO
 * RoleServiceImpl.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/27 22:23
 * @see RoleServiceImpl
 **/
@DubboService(version = "0.0.1", delay = -1,timeout = 6000,retries = 5)
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private HrRoleMapper hrRoleMapper;

    @Override
    public List<Role> getRolesByHrId(Integer hrId) {
        return roleMapper.findRolesByHrId(hrId);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.selectAll();
    }

    @Override
    public int addRole(Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        return roleMapper.insert(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteRoleById(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
