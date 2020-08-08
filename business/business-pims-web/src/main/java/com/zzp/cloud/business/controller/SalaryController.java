package com.zzp.cloud.business.controller;

import com.zzp.cloud.common.dto.RespBean;
import com.zzp.cloud.provider.api.SalaryService;
import com.zzp.cloud.provider.domain.Salary;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 工资账套
 * <p>
 *  工资的CRUD
 *  ssd.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/5 1:29
 * @see  com.zzp.cloud.business.controller
 **/
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {
    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private SalaryService salaryService;

    @GetMapping("/")
    public List<Salary> getAllSalaries() {
        return salaryService.getAllSalaries();
    }

    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary) {
        if (salaryService.addSalary(salary) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteSalaryById(@PathVariable Integer id) {
        if (salaryService.deleteSalaryById(id) == 1) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @PutMapping("/")
    public RespBean updateSalaryById(@RequestBody Salary salary) {
        if (salaryService.updateSalary(salary) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
