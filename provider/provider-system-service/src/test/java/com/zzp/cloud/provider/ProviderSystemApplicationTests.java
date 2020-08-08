package com.zzp.cloud.provider;

import com.google.common.collect.Maps;
import com.zzp.cloud.common.dto.cms.DepartmentDto;
import com.zzp.cloud.common.utils.JsonUtils;
import com.zzp.cloud.common.utils.OkHttpClientUtil;
import com.zzp.cloud.provider.api.DepartmentService;
import com.zzp.cloud.provider.api.MenuService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@SpringBootTest
@EnableCaching
@Transactional
@Rollback
public class ProviderSystemApplicationTests {

    @Resource
    private MenuService menuService;

    @Test
    void test1(){
        menuService.getMenuWithRole().forEach(System.out::println);
    }

    @Test
    void test2(){
        getToken("","").forEach((k,y)-> System.out.println(k+"---"+y));
    }

    @Test
    void test3(){
        menuService.getMenusByHrId(3).forEach(System.out::println);
    }

    private Map<String, Object> getToken(String username, String password) {

        var instance = OkHttpClientUtil.getInstance();
        // 通过 HTTP 客户端请求登录接口
        Map<String, String> params = Maps.newHashMap();
        params.put("username", "admin");
        params.put("password", "123");
        var url = "http://localhost:9001/doLogin";
        try {
            var response = instance.postData(url, params);
            var jsonString = Objects.requireNonNull(response.body()).string();
            var stringObjectMap = JsonUtils.json2map(jsonString);
            return stringObjectMap;
            // 发送登录日志
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Resource
    private DepartmentService departmentService;
    @Test
    void test5(){
        var d = new DepartmentDto();
        d.setId(1);
        departmentService.deleteDepById(d);
        System.out.println(d.getResult());
    }

    @Test
    void test6(){
        var mids = Arrays.array(9, 10, 11);
        var rid = 3;
        System.out.println(menuService.updateMenuRole(rid, mids));
    }
}
