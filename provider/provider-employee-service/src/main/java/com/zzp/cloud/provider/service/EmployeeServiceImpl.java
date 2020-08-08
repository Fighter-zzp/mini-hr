package com.zzp.cloud.provider.service;

import com.zzp.cloud.common.dto.RespPageBean;
import com.zzp.cloud.common.dto.cms.EmployeeDto;
import com.zzp.cloud.provider.api.EmployeeService;
import com.zzp.cloud.provider.mapper.EmployeeMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 员工服务实现类
 * <p>
 *  //TODO
 *  EmployeeServiceImpl.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 18:00
 * @see  com.zzp.cloud.provider.service
 **/
@DubboService(version = "0.0.1", delay = -1, timeout = 6000, retries = 5)
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public RespPageBean<EmployeeDto> getEmployeeByPage(Integer page, Integer size, EmployeeDto employee, Date[] beginDateScope) {
        // 拆分page为limit的两个参数
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        // 获取到数据
        var data = employeeMapper.findEmployeeByPage(page, size, employee, beginDateScope);
        // 查询总数据条数
        var total = employeeMapper.findTotalCount(employee, beginDateScope);
        var bean = new RespPageBean<EmployeeDto>();
        return bean.setData(data).setTotal(total);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addEmps(List<EmployeeDto> list) {
        return employeeMapper.addEmps(list);
    }

    @Override
    public RespPageBean<EmployeeDto> getEmployeeByPageWithSalary(Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        var list = employeeMapper.findEmployeeByPageWithSalary(page, size);
        var respPageBean = new RespPageBean<EmployeeDto>();
        respPageBean.setData(list);
        respPageBean.setTotal(employeeMapper.findTotalCount(null, null));
        return respPageBean;
    }

    @Override
    public Integer updateEmployeeSalaryById(Integer eid, Integer sid) {
        return employeeMapper.updateEmployeeSalaryById(eid,sid);
    }
}
