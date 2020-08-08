package com.zzp.cloud.provider.api;

import com.zzp.cloud.common.dto.RespPageBean;
import com.zzp.cloud.common.dto.cms.EmployeeDto;

import java.util.Date;
import java.util.List;

/**
 * 雇员服务
 * <p>
 * //TODO
 * EmployeeService.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/2 17:44
 * @see com.zzp.cloud.provider.api
 **/
public interface EmployeeService {
    /**
     * 在规定时间范围内获取员工的分页信息
     * @param page 页数
     * @param size 条数
     * @param employee 员工
     * @param beginDateScope  开始时间
     * @return {@link RespPageBean}
     */
    RespPageBean<EmployeeDto> getEmployeeByPage(Integer page, Integer size, EmployeeDto employee, Date[] beginDateScope);

    /**
     * 添加多个员工
     * @param list 用过dto集合
     * @return .
     */
    int addEmps(List<EmployeeDto> list);

    /**
     * 获取员工带薪水的信息
     * @param page 页数
     * @param size row
     * @return
     */
    RespPageBean<EmployeeDto> getEmployeeByPageWithSalary(Integer page, Integer size);

    /**
     * 更新员工薪水
     * @param eid 员工id
     * @param sid 薪水id
     * @return row
     */
    Integer updateEmployeeSalaryById(Integer eid, Integer sid);
}
