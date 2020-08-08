package com.zzp.cloud.provider.mapper;

import com.zzp.cloud.provider.domain.Menu;
import com.zzp.cloud.provider.dto.MenuDto;
import tk.mybatis.mapper.CommonMapper;

import java.util.List;


/**
 * MenuMapper
 * <p>
 *  //TODO
 *  MenuMapper.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/27 23:37
 * @see  MenuMapper
 **/
public interface MenuMapper extends CommonMapper<Menu> {
    /**
     * 查询操作有的菜单和对应的权限
     * @return {@link MenuDto}
     */
    List<MenuDto> findAllMenusWithRole();

    /**
     * 查询hr有权限的所有菜单
     * @param hrId hr的id
     * @return {@link List<MenuDto>}
     */
    List<MenuDto> findMenusByHrId(Integer hrId);

    /**
     * 获取所有带children的menu （三代）
     * @return {@link List<MenuDto>}
     */
    List<MenuDto> findAllMenusWithChildren();

    /**
     * 根据rid查mids
     * @param rid .
     * @return .
     */
    List<Integer> findMidsByRid(Integer rid);
}