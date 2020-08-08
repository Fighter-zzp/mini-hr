package com.zzp.cloud.business.controller;

import com.zzp.cloud.common.dto.RespBean;
import com.zzp.cloud.common.dto.RespPageBean;
import com.zzp.cloud.common.dto.cms.EmployeeDto;
import com.zzp.cloud.provider.api.EmployeeService;
import com.zzp.cloud.provider.api.SalaryService;
import com.zzp.cloud.provider.domain.Salary;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 工资数据信息
 * <p>
 *  //TODO
 *  SobConfigController.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/5 1:33
 * @see  com.zzp.cloud.business.controller
 **/
@RestController
@RequestMapping("/salary/sobcfg")
public class SobConfigController {
    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private EmployeeService employeeService;

    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private SalaryService salaryService;

    @GetMapping("/")
    public RespPageBean<EmployeeDto> getEmployeeByPageWithSalary(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeByPageWithSalary(page, size);
    }

    @GetMapping("/salaries")
    public List<Salary> getAllSalaries() {
        return salaryService.getAllSalaries();
    }

    @PutMapping("/")
    public RespBean updateEmployeeSalaryById(Integer eid, Integer sid) {
        var result = employeeService.updateEmployeeSalaryById(eid, sid);
        if (result == 1 || result == 2) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
