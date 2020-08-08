package com.zzp.cloud.business.controller;

import com.zzp.cloud.common.dto.RespBean;
import com.zzp.cloud.common.dto.emp.HrDto;
import com.zzp.cloud.provider.api.HrService;
import com.zzp.cloud.provider.api.RoleService;
import com.zzp.cloud.provider.domain.Hr;
import com.zzp.cloud.provider.domain.Role;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统更新hr信息的
 * <p>
 *  //TODO
 *  SystemHrController.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/5 0:07
 * @see  com.zzp.cloud.business.controller
 **/
@RestController
@RequestMapping("/hr")
public class SystemHrController {
    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private HrService hrService;

    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private RoleService roleService;

    @GetMapping("/")
    public List<HrDto> getAllHrs(@RequestParam("id") Integer hrId, @RequestParam("keywords")String keywords) {
        return hrService.findAllHrs(hrId,keywords);
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (hrService.updateHr(hr) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/role")
    public RespBean updateHrRole(Integer hrid, Integer[] rids) {
        if (hrService.updateHrRole(hrid, rids)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteHrById(@PathVariable Integer id) {
        if (hrService.deleteHrById(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

}
