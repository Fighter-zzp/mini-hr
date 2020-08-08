package com.zzp.cloud.configuration.security.costom;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 自定义url决策管理
 * <p>
 *  //TODO
 *  CustomUrlDecisionManager.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/7/28 0:49
 * @see  CustomUrlDecisionManager
 **/
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    /**
     * 决策控制
     * @param authentication 认证
     * @param object 对象
     * @param configAttributes 配置属性
     * @throws AccessDeniedException 决策异常
     * @throws InsufficientAuthenticationException 异常
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            var needRole = configAttribute.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登录，请登录!");
                }else {
                    return;
                }
            }
            // 获取权限--之前自定义meta资源的权限字符数组
            var authorities = authentication.getAuthorities();
            for (var authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员!");
    }

    /**
     * 表示是否AccessDecisionManager能够呈现与传递的过程授权请求ConfigAttribute 。
     * @param attribute 配置属性
     * @return 是否
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    /**
     * 指示是否AccessDecisionManager实现能够针对所指示的担保对象提供类型访问控制决策
     * @param clazz 对象反射
     * @return 是否
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
