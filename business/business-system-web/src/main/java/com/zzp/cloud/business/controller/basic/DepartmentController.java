package com.zzp.cloud.business.controller.basic;


import com.zzp.cloud.common.dto.RespBean;
import com.zzp.cloud.common.dto.cms.DepartmentDto;
import com.zzp.cloud.provider.api.DepartmentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DepartmentController
 * <p>
 *  //TODO
 *  DepartmentController.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/4 23:36
 * @see  com.zzp.cloud.business.controller.basic
 **/
@RestController
@RequestMapping("/basic/department")
public class DepartmentController {
    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private DepartmentService departmentService;


    @GetMapping("/")
    public List<DepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/")
    public RespBean addDep(@RequestBody DepartmentDto dep) {
        departmentService.addDep(dep);
        if (dep.getResult() == 1) {
            return RespBean.ok("添加成功", dep);
        }
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDepById(@PathVariable Integer id) {
        var dep = new DepartmentDto();
        dep.setId(id);
        departmentService.deleteDepById(dep);
        if (dep.getResult() == -2) {
            return RespBean.error("该部门下有子部门，删除失败");
        } else if (dep.getResult() == -1) {
            return RespBean.error("该部门下有员工，删除失败");
        } else if (dep.getResult() == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}