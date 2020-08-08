package com.zzp.cloud.provider.mapper;

import com.zzp.cloud.provider.domain.Role;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.CommonMapper;

import java.util.List;

/**
 * RoleMapper
 * <p>
 *  //TODO
 *  RoleMapper.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/27 22:19
 * @see  RoleMapper
 **/
public interface RoleMapper extends CommonMapper<Role> {
    /**
     * 通过hrid查询role
     * @param hrId hrld
     * @return List<Role>
     */
    List<Role> findRolesByHrId(@Param("hrId")Integer hrId);

}