package com.zzp.cloud.business.service;

import com.zzp.cloud.configuration.security.domain.HrDetails;
import com.zzp.cloud.provider.api.HrService;
import com.zzp.cloud.provider.api.RoleService;
import com.zzp.cloud.provider.domain.Role;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 用户详情服务实现
 * <p>
 * 实现security服务接口
 * UserDetailsServiceImpl.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/27 22:54
 * @see UserDetailsServiceImpl
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @DubboReference(version = "0.0.1", timeout = 12000, retries = 5)
    private HrService hrService;

    @DubboReference(version = "0.0.1", timeout = 12000, retries = 5)
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var hrDetails = new HrDetails();
        var user = hrService.loadByUserName(username);
        if (username.equals(user.getUsername())) {
            // 获取数据库hr信息
            var hr = Optional.of(user).orElseThrow(() -> new UsernameNotFoundException("用户名不存在！"));
            // 复制到实体模型hrDetails
            BeanUtils.copyProperties(hr, hrDetails);
            // 添加用户登录权限
            var roles = roleService.getRolesByHrId(hrDetails.getId());
            roles.add(new Role(0,"ROLE_LOGIN","用户登录"));
            // 设置权限
            hrDetails.setRoles(roles);
        }
        return hrDetails;
    }
}
