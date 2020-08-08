package com.zzp.cloud.business.controller.config;

import com.zzp.cloud.provider.api.HrService;
import com.zzp.cloud.provider.api.MenuService;
import com.zzp.cloud.provider.dto.MenuDto;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 后台系统配置信息controller
 * <p>
 *  给前端系统的配置信息
 *  SystemConfigController.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/1 17:16
 * @see  com.zzp.cloud.business.controller.config
 **/
@RequestMapping("/config")
@RestController
public class SystemConfigController {
    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private MenuService menuService;

    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private HrService hrService;

    @GetMapping("/menu")
    public List<MenuDto> getMenusByHrId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var hr = hrService.loadByUserName((String) authentication.getPrincipal());
        return menuService.getMenusByHrId(hr.getId());
    }

}
