package com.zzp.cloud.business.controller;

import com.zzp.cloud.common.dto.cms.DepartmentDto;
import com.zzp.cloud.provider.api.DepartmentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * //TODO
 * DepartmentController.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/2 20:13
 * @see com.zzp.cloud.business.controller
 **/
@RestController
@RequestMapping("/employee/basic")
public class DepartmentController {

    @DubboReference(version = "0.0.1", retries = 5, timeout = 12000)
    private DepartmentService departmentService;

    @GetMapping("/deps")
    public List<DepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

}
