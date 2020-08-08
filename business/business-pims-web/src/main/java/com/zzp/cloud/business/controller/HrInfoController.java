package com.zzp.cloud.business.controller;

import com.zzp.cloud.business.feign.HrWebFeign;
import com.zzp.cloud.business.service.OssCloudService;
import com.zzp.cloud.common.dto.RespBean;
import com.zzp.cloud.common.utils.JsonUtils;
import com.zzp.cloud.common.vo.HrVo;
import com.zzp.cloud.configuration.security.domain.HrDetails;
import com.zzp.cloud.provider.api.HrService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

/**
 * hr信息类
 * <p>
 *  //TODO
 *  HrInfoController.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/3 14:20
 * @see  com.zzp.cloud.business.controller
 **/
@RestController
@RequestMapping("/hr")
public class HrInfoController {
    @DubboReference(version = "0.0.1",timeout = 12000,retries = 5)
    private HrService hrService;

    @Resource
    private HrWebFeign hrWebFeign;

    @Resource
    private OssCloudService ossCloudService;


    @GetMapping("/info")
    public HrDetails info(){
        var jsonStr = hrWebFeign.hrInfo();
        HrDetails hd = null;
        try {
            hd = JsonUtils.json2pojo(jsonStr, HrDetails.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    @PutMapping("/info")
    public RespBean updateHr(@RequestBody HrVo hrvo) {
        var res = hrWebFeign.update(hrvo);
        if ("1".equals(res)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PutMapping("/pass")
    public RespBean updateHrPasswd(@RequestBody Map<String, Object> info) {
        var oldPass = (String) info.get("oldpass");
        var pass = (String) info.get("pass");
        var hrId = (Integer) info.get("hrid");
        if (hrService.updateHrPasswd(oldPass, pass, hrId)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PostMapping("/userface")
    public RespBean updateHrUserFace(@RequestParam("file") MultipartFile file,
                                     @RequestParam("id")Integer id,@RequestParam("userface")String userface) {

        var fileName = Optional.ofNullable(userface).map(uf -> uf.substring((uf.lastIndexOf("/") + 1))).orElse("");
        var rb = ossCloudService.uploadFileOss(file,fileName);
        if (rb.getStatus()==200){
            // 获取上传好的图像地址
            var url = rb.getMsg();
            if ("1".equals(hrWebFeign.updateHrUserFace(url,id))) {
                return RespBean.ok("更新成功!", url);
            }else {
                ossCloudService.deleteFileOss((String) rb.getData());
                return RespBean.error("更新失败!");
            }
        }
        return RespBean.error("更新失败!");
    }

}
