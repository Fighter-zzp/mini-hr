package com.zzp.cloud.provider;

import com.zzp.cloud.common.dto.RespPageBean;
import com.zzp.cloud.common.dto.cms.EmployeeDto;
import com.zzp.cloud.common.utils.JsonUtils;
import com.zzp.cloud.provider.api.EmployeeService;
import com.zzp.cloud.provider.api.HrService;
import com.zzp.cloud.provider.domain.Hr;
import com.zzp.cloud.provider.mapper.HrMapper;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MapperUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@SpringBootTest
@Rollback(true)
@Transactional
public class ProviderHrApplicationTests {
    @Resource
    private HrService hrService;

    @Test
    void test1(){
        System.out.println(hrService.loadByUserName("admin"));
    }

    @Resource
    private EmployeeService employeeService;

    @Test
    void test2(){
        var sf = new SimpleDateFormat("yyyy-MM-dd");
        var ld1 =  LocalDate.of(2018,1,1);
        var ld2 =  LocalDate.of(2020,1,1);
        var dates = Arrays.array(convert(ld1), convert(ld2));

        employeeService
                .getEmployeeByPage(2,3,null,dates)
                .getData()
                .forEach(System.out::println);
    }

    private Date convert(LocalDate localDate){
        var zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }
    @Resource
    private HrMapper hrMapper;

    @Test
    void test3(){
        System.out.println(hrMapper.updatePasswd(3, "123"));
    }

    @Test
    void test4() throws Exception {
        var e = employeeService.getEmployeeByPageWithSalary(1, 5);
        System.out.printf("有%d条数据",e.getTotal());
        System.out.println(JsonUtils.obj2json(e.getData()));
    }


    @Test
    void test5(){
        // hrService.getAllHrsExceptCurrentHr().forEach(System.out::println);
        var example = new Example(Hr.class);
        var c = MapperUtils.andEqualOrNo(example, true, "id", "3");
        c.andLike("address","%广州%");
        hrMapper.selectByExample(example).forEach(System.out::println);
    }
}
