package com.zzp.cloud.provider.service;

import com.zzp.cloud.provider.api.SalaryService;
import com.zzp.cloud.provider.domain.Salary;
import com.zzp.cloud.provider.mapper.SalaryMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * SalaryServiceImpl
 * <p>
 *  //TODO
 *  SalaryServiceImpl.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/5 1:11
 * @see  com.zzp.cloud.provider.service
 **/
@DubboService(version = "0.0.1", delay = -1, timeout = 6000, retries = 5)
public class SalaryServiceImpl implements SalaryService {

    @Resource
    private SalaryMapper salaryMapper;

    @Override
    public List<Salary> getAllSalaries() {
        return salaryMapper.selectAll();
    }

    @Override
    public Integer addSalary(Salary salary) {
        salary.setCreateDate(new Date());
        return salaryMapper.insertSelective(salary);
    }

    @Override
    public Integer deleteSalaryById(Integer id) {
        return salaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateSalary(Salary salary) {
        return salaryMapper.updateByPrimaryKeySelective(salary);
    }
}
