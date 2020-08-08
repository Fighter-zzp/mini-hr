package com.zzp.cloud.business.controller.basic;


import com.zzp.cloud.common.dto.RespBean;
import com.zzp.cloud.provider.api.MenuService;
import com.zzp.cloud.provider.api.RoleService;
import com.zzp.cloud.provider.domain.Menu;
import com.zzp.cloud.provider.domain.Role;
import com.zzp.cloud.provider.dto.MenuDto;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限控制
 * <p>
 *  //TODO
 *  PermissController.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/4 22:54
 * @see  com.zzp.cloud.business.controller.basic
 **/
@RestController
@RequestMapping("/basic/permiss")
public class PermissionController {
    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private RoleService roleService;
    @DubboReference(version = "0.0.1",retries = 5,timeout = 12000)
    private MenuService menuService;


    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
    @GetMapping("/menus")
    public List<MenuDto> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid) {
        return menuService.getMidsByRid(rid);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        if (menuService.updateMenuRole(rid, mids)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        if (roleService.addRole(role) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleById(@PathVariable Integer rid) {
        if (roleService.deleteRoleById(rid) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
