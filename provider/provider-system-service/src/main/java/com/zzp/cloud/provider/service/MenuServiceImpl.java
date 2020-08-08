package com.zzp.cloud.provider.service;

import com.zzp.cloud.provider.api.MenuService;
import com.zzp.cloud.provider.domain.MenuRole;
import com.zzp.cloud.provider.dto.MenuDto;
import com.zzp.cloud.provider.mapper.MenuMapper;
import com.zzp.cloud.provider.mapper.MenuRoleMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 菜单服务实现类
 * <p>
 * //TODO
 * MenuServiceImpl.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/27 23:38
 * @see MenuServiceImpl
 **/
@DubboService(version = "0.0.1", delay = -1)
@CacheConfig(cacheNames = "menus_cache",cacheManager = "customCacheManager")
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private MenuRoleMapper menuRoleMapper;

    @Cacheable(key = "#root.methodName")
    @Override
    public List<MenuDto> getMenuWithRole() {
        return menuMapper.findAllMenusWithRole();
    }

    @Override
    public List<MenuDto> getMenusByHrId(Integer hrId) {
        return menuMapper.findMenusByHrId(hrId);
    }

    @Override
    public List<MenuDto> getAllMenus() {
        return menuMapper.findAllMenusWithChildren();
    }

    @Override
    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.findMidsByRid(rid);
    }

    /**
     * 当mids无的时候相对于删除
     * @param rid .
     * @param mids .
     * @return .
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        var example = new Example(MenuRole.class);
        example.createCriteria().andEqualTo("rid",rid);
        menuRoleMapper.deleteByExample(example);

        // 这里相当于删除
        if (mids==null||mids.length==0){
            return true;
        }
        // 不想写sql语句了
        var mrList = Stream.of(mids).map(mid -> {
            var mr = new MenuRole();
            mr.setMid(mid);
            mr.setRid(rid);
            return mr;
        }).collect(Collectors.toList());
        var i = menuRoleMapper.insertList(mrList);
        return i==mids.length;
    }
}
