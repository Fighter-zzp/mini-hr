package com.zzp.cloud.business.controller;

import com.zzp.cloud.provider.api.EmployeeService;
import com.zzp.cloud.provider.api.NationService;
import com.zzp.cloud.provider.domain.Nation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * NationController
 * <p>
 *  //TODO
 *  NationController.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 20:07
 * @see  com.zzp.cloud.business.controller
 **/
@RestController
@RequestMapping("/employee/basic")
public class NationController {

    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private NationService nationService;

    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }
}
