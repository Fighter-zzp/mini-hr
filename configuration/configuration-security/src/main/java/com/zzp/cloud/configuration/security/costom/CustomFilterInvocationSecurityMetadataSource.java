package com.zzp.cloud.configuration.security.costom;

import com.zzp.cloud.provider.api.MenuService;
import com.zzp.cloud.provider.domain.Role;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

/**
 * 自定义媒体资源
 * <p>
 * 这个类的作用，主要是根据用户传来的请求地址，分析出请求需要的角色
 * CustomFilterInvocationSecurityMetadataSource.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/27 23:24
 * @see CustomFilterInvocationSecurityMetadataSource
 **/
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @DubboReference(version = "0.0.1", timeout = 12000, retries = 5)
    private MenuService menuService;

    /**
     * URLs字符串匹配
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 获取授权信息
     *
     * @param o 安全对象 需要SecurityMetadataSource对象支持
     * @return {@link Collection<ConfigAttribute>}
     * @throws IllegalArgumentException 非法
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 得到请求url
        var requestUrl = ((FilterInvocation) o).getRequestUrl();
        var menuWithRoles = menuService.getMenuWithRole();
        for (var menu : menuWithRoles) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                var roles = menu.getRoles();
                var rolesStr = roles.stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(rolesStr);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 是否ecurityMetadataSource对象支持
     *
     * @param aClass 对象反射
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
