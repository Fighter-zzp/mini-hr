package com.zzp.cloud.provider.api;

import com.zzp.cloud.provider.dto.MenuDto;

import java.util.List;

/**
 * 菜单服务
 * <p>
 *  //TODO
 *  MenuService.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/27 23:38
 * @see  MenuService
 **/
public interface MenuService {
    /**
     * 获取菜单及对应的权限
     * @return {@link List<MenuDto>}
     */
    List<MenuDto> getMenuWithRole();

    /**
     * 查询hr有权限的所有菜单
     * @param hrId hr的id
     * @return {@link List<MenuDto>}
     */
    List<MenuDto> getMenusByHrId(Integer hrId);

    /**
     * 获取所有带子菜单的 菜单集合
     * @return @link List<MenuDto>}
     */
    List<MenuDto> getAllMenus();

    /**
     * 由权限id获取所有菜单id
     * @param rid .
     * @return {@link List<Integer>}
     */
    List<Integer> getMidsByRid(Integer rid);

    /**
     * 由权限id更新菜单ids
     * 即为权限添加可使用的菜单
     * @param rid .
     * @param mids .
     * @return .
     */
    boolean updateMenuRole(Integer rid, Integer[] mids);
}
