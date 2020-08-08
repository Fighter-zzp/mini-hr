package com.zzp.cloud.provider.mapper;

import com.zzp.cloud.common.dto.RespPageBean;
import com.zzp.cloud.common.dto.cms.EmployeeDto;
import com.zzp.cloud.provider.domain.Employee;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.CommonMapper;

import java.util.Date;
import java.util.List;

/**
 * EmployeeMapper
 * <p>
 * //TODO
 * EmployeeMapper.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/2 17:51
 * @see com.zzp.cloud.provider.mapper
 **/
public interface EmployeeMapper extends CommonMapper<Employee> {
    /**
     * 根据页码页数查询数据
     *
     * @param page           页数
     * @param size           条数
     * @param employee       员工
     * @param beginDateScope 开始时间
     * @return {@link RespPageBean}
     */
    List<EmployeeDto> findEmployeeByPage(@Param("page") Integer page,
                                         @Param("size") Integer size,
                                         @Param("emp") EmployeeDto employee,
                                         @Param("beginDateScope") Date[] beginDateScope);

    /**
     * 查找用过总条数
     *
     * @param employee       员工传输类
     * @param beginDateScope 时间范围
     * @return long
     */
    Long findTotalCount(@Param("emp") EmployeeDto employee, @Param("beginDateScope") Date[] beginDateScope);

    /**
     * 插入多喝员工
     *
     * @param list .
     * @return .
     */
    Integer addEmps(@Param("list") List<EmployeeDto> list);

    /**
     * 查询带薪水的员工并且分页
     *
     * @param page .
     * @param size .
     * @return .
     */
    List<EmployeeDto> findEmployeeByPageWithSalary(@Param("page") Integer page, @Param("size") Integer size);

    /**
     * 更新员工薪水
     * 使用 Replace into
     * @param eid .
     * @param sid .
     * @return .
     */
    int updateEmployeeSalaryById(@Param("eid") Integer eid, @Param("sid") Integer sid);
}