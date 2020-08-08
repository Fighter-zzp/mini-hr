package com.zzp.cloud.business.feign;

import com.zzp.cloud.common.vo.HrVo;
import com.zzp.cloud.configuration.fegin.configure.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 系统web的fegin服务
 * <p>
 * //TODO
 * SystemFegin.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/1 18:06
 * @see com.zzp.cloud.business.feign
 **/
@FeignClient(value = "business-auth-web", path = "auth"
        , configuration = FeignRequestConfiguration.class)
public interface HrWebFeign {

    /**
     * restful风格获取用户的信息
     *
     * @return json字符串
     */
    @GetMapping("info")
    String hrInfo();

    /**
     * 更新用户
     *
     * @param hr .
     * @return .
     */
    @PutMapping("update")
    String update(@RequestBody HrVo hr);

    /**
     * 更新头像
     *
     * @param url 头像网址
     * @param id  id号
     * @return .
     */
    @PostMapping("/userFace")
    String updateHrUserFace(@RequestParam("url") String url, @RequestParam("id")Integer id);
}
