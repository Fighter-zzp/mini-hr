package com.zzp.cloud.provider.service;

import com.zzp.cloud.common.dto.cms.DepartmentDto;
import com.zzp.cloud.provider.api.DepartmentService;
import com.zzp.cloud.provider.domain.Department;
import com.zzp.cloud.provider.mapper.DepartmentMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DepartmentServiceImpl
 * <p>
 *  //TODO
 *  DepartmentServiceImpl.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 21:17
 * @see  com.zzp.cloud.provider.service
 **/
@DubboService(version = "0.0.1", delay = -1)
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    @Override
    public void addDep(DepartmentDto dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
    }

    @Override
    public void deleteDepById(DepartmentDto dep) {
        departmentMapper.deleteDepById(dep);
    }

    @Override
    public List<Department> getAllDepartmentsWithOutChildren() {
        return departmentMapper.selectAll();
    }

    @Override
    public List<DepartmentDto> getAllDepartmentDtoWithOutChildren() {
        return departmentMapper.selectAll().stream().map(dep->{
            var dto = new DepartmentDto();
            dto.setId(dep.getId());
            dto.setDepPath(dep.getDeppath());
            dto.setEnabled(dep.getEnabled());
            dto.setParentId(dep.getParentid());
            dto.setName(dep.getName());
            return dto;
        }).collect(Collectors.toList());
    }

}
