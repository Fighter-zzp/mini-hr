package com.zzp.cloud.provider.api;

import com.zzp.cloud.provider.domain.Salary;

import java.util.List;

/**
 * 薪水服务
 * <p>
 * //TODO
 * SalaryService.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/5 1:06
 * @see com.zzp.cloud.provider.api
 **/
public interface SalaryService {
    /**
     * 获取哦所有薪水信息
     *
     * @return {@link List<Salary>}
     */
    List<Salary> getAllSalaries();

    /**
     * 添加薪水
     *
     * @param salary .
     * @return .
     */
    Integer addSalary(Salary salary);

    /**
     * 通过id删除薪水
     *
     * @param id .
     * @return .
     */
    Integer deleteSalaryById(Integer id);

    /**
     * 更新
     *
     * @param salary .
     * @return .
     */
    Integer updateSalary(Salary salary);

}
