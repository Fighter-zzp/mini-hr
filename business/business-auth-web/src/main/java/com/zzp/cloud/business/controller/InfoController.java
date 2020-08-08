package com.zzp.cloud.business.controller;

import com.zzp.cloud.common.vo.HrVo;
import com.zzp.cloud.configuration.security.domain.HrDetails;
import com.zzp.cloud.provider.api.HrService;
import com.zzp.cloud.provider.domain.Hr;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 对security与数据库信息的修改
 * <p>
 * //TODO
 * InfoController.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/3 15:43
 * @see com.zzp.cloud.business.controller
 **/
@RestController
@RequestMapping("/auth")
@Slf4j
public class InfoController {
    @DubboReference(version = "0.0.1", timeout = 12000, retries = 5)
    private HrService hrService;

    @GetMapping("info")
    public HrDetails info(Authentication auth) {
        return (HrDetails) auth.getPrincipal();
    }

    @Resource
    private TokenStore tokenStore;

    @PutMapping("update")
    public Integer update(@RequestBody HrVo hrVo, HttpServletRequest req, Authentication auth) {
        try {
            // hr转换为HrDetails
            var hrDetails = Optional.ofNullable(hrVo).map(h -> {
                var hr = new Hr();
                BeanUtils.copyProperties(hrVo,hr);
                // 从认证服务器中或去HrDetails
                var hd = (HrDetails) auth.getPrincipal();
                // 数据库更新更改才更新认证服务器里的信息
                if (hrService.updateHr(hr) > 0) {
                    hd.setName(h.getName());
                    hd.setTelephone(h.getTelephone());
                    hd.setPhone(h.getPhone());
                    hd.setAddress(h.getAddress());
                    return hd;
                }
                return hd;
            }).orElseThrow(() -> new NoSuchElementException("hr的信息为空，无法更新！"));
            // 设置auth
           updateAuthentication(hrDetails,req,auth);
        } catch (Exception e) {
            e.printStackTrace();
            // 更新失败
            return -1;
        }
        // 更新成功
        return 1;
    }

    @PostMapping("/userFace")
    public Integer updateHrUserface(@RequestParam("url") String url, @RequestParam("id")Integer id,HttpServletRequest req,Authentication auth) {
        if (hrService.updateUserFace(url, id) == 1) {
            var hrDetails = (HrDetails) auth.getPrincipal();
            hrDetails.setUserface(url);
            try {
                updateAuthentication(hrDetails,req,auth);
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }else {
            return -1;
        }
        return 1;
    }

    private void updateAuthentication(HrDetails hrDetails,HttpServletRequest req,Authentication auth) throws Exception{
        if (auth instanceof OAuth2Authentication){
            // 设置
            var upat = new UsernamePasswordAuthenticationToken(hrDetails,
                    auth.getCredentials(), auth.getAuthorities());
            var oa = (OAuth2Authentication)auth;
            var token = req.getHeader("Authorization").substring("Bearer ".length());
            var accessToken = tokenStore.readAccessToken(token);
            var authentication = new OAuth2Authentication(oa.getOAuth2Request(), upat);
            tokenStore.storeAccessToken(accessToken,authentication);
        }else {
            log.error("auth不是OAuth2Authentication，check一下原因");
            throw new ClassCastException();
        }
    }

}
