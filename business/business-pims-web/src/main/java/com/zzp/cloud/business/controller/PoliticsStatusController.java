package com.zzp.cloud.business.controller;

import com.zzp.cloud.provider.api.EmployeeService;
import com.zzp.cloud.provider.api.PoliticsStatusService;
import com.zzp.cloud.provider.domain.Nation;
import com.zzp.cloud.provider.domain.Politicsstatus;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * NationController
 * <p>
 *  //TODO
 *  PoliticsStatusController.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 20:07
 * @see  com.zzp.cloud.business.controller
 **/
@RestController
@RequestMapping("/employee/basic")
public class PoliticsStatusController {

    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private PoliticsStatusService politicsStatusService;

    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsStatus() {
        return politicsStatusService.getAllPoliticsStatus();
    }
}
