package com.zzp.cloud.business.controller;

import com.zzp.cloud.common.dto.RespBean;
import com.zzp.cloud.common.utils.JsonUtils;
import com.zzp.cloud.common.utils.VerificationCode;
import com.zzp.cloud.configuration.security.domain.HrDetails;
import com.zzp.cloud.provider.domain.Hr;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * 登录控制
 * <p>
 * //TODO
 * LoginController.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/28 17:25
 * @see com.zzp.cloud.business.controller
 **/
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Resource
    private TokenStore tokenStore;


    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        var code = new VerificationCode();
        var image = code.getImage();
        var text = code.getText();
        var session = request.getSession(true);
        session.setAttribute("verify_code", text);
        VerificationCode.output(image, resp.getOutputStream());
    }

   @GetMapping("/logout")
    public RespBean logout(HttpServletRequest req) {
        // 获取token
        var token = (String)req.getParameter("tk");
       if (Strings.isBlank(token)){
           return RespBean.error("用户异常，请联系管理员！");
       }
        // 注销之前token
        var oAuth2AccessToken = tokenStore.readAccessToken(token);
        // 移除
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return RespBean.ok("用户已注销");
    }


    /**
     * 资源请求测试 admin 能进
     *
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_admin')")
    @GetMapping("/employee/basic/hello")
    public RespBean test() throws Exception {
        // 测试admin能否看到这个
        // 获取认证信息
        var hr = JsonUtils.json2pojoByTree("{status:今天天气真好,test:看到了吗}", "data", Hr.class);
        return RespBean.ok("信息获取",hr);
    }

    /**
     * 资源请求测试 admin 不能进
     *
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_FATHER')")
    @GetMapping("/employee/advanced/hello")
    public String test1() {
        // 测试admin能否看到这个
        return "Oh my god! You can see me!";
    }

    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }

}
