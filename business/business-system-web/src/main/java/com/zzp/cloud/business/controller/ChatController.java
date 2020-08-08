package com.zzp.cloud.business.controller;

import com.zzp.cloud.provider.api.HrService;
import com.zzp.cloud.provider.domain.Hr;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
/**
 * chat控制层
 * <p>
 *  基于hr列表
 *  ChatController.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/5 16:57
 * @see  com.zzp.cloud.business.controller
 **/
@RestController
@RequestMapping("/chat")
public class ChatController {
    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private HrService hrService;

    @GetMapping("/hrs")
    public List<Hr> getAllHrs(Integer id) {
        return hrService.getAllHrsExceptCurrentHr(id);
    }
}
