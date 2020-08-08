package com.zzp.cloud.business.controller;

import com.zzp.cloud.common.dto.RespBean;
import com.zzp.cloud.common.dto.RespPageBean;
import com.zzp.cloud.common.dto.cms.EmployeeDto;
import com.zzp.cloud.common.utils.PoiUtils;
import com.zzp.cloud.provider.api.*;
import com.zzp.cloud.provider.domain.Joblevel;
import com.zzp.cloud.provider.domain.Position;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 员工基础服务controller
 * <p>
 * //TODO
 * EmployeeController.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/2 17:38
 * @see com.zzp.cloud.business.controller
 **/
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {
    @DubboReference(version = "0.0.1", retries = 5, timeout = 12000)
    private HrService hrService;

    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private EmployeeService employeeService;

    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private NationService nationService;
    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private PoliticsStatusService politicsstatusService;
    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private JobLevelService jobLevelService;
    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private PositionService positionService;
    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private DepartmentService departmentService;

    @GetMapping("/")
    public RespPageBean<EmployeeDto> getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          EmployeeDto employee, Date[] beginDateScope) {
        return employeeService.getEmployeeByPage(page, size, employee,beginDateScope);
    }

    /**
     * 提供员工额外信息
     */

    @GetMapping("/joblevels")
    public List<Joblevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    /** end **/

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData() {
        var list = employeeService.getEmployeeByPage(null, null, new EmployeeDto(), null).getData();
        return PoiUtils.employee2Excel(list);
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        var list = PoiUtils.excel2Employee(file,
                nationService.getAllNationDto(),
                politicsstatusService.getAllPoliticsStatusDto(),
                departmentService.getAllDepartmentDtoWithOutChildren(),
                positionService.getAllPositionDto(),
                jobLevelService.getAllJobLevelDto());
        if (employeeService.addEmps(list) == list.size()) {
            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败");
    }

}

