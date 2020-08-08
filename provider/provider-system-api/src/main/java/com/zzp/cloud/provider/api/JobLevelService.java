package com.zzp.cloud.provider.api;

import com.zzp.cloud.common.dto.cms.JobLevelDto;
import com.zzp.cloud.provider.domain.Joblevel;

import java.util.List;

/**
 * JobLevelService
 * <p>
 *  //TODO
 *  JobLevelService.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 20:15
 * @see  com.zzp.cloud.provider.api
 **/
public interface JobLevelService {
    /**
     * 过去所有职位等级
     * @return .
     */
    List<Joblevel> getAllJobLevels();

    /**
     * 获取有职位dto构成的集合
     * @return .
     */
    List<JobLevelDto> getAllJobLevelDto();

    /**
     * 添加
     * @param jobLevel 职位
     * @return Integer
     */
     Integer addJobLevel(Joblevel jobLevel);

    /**
     * 更新
     * @param jobLevel 职位
     * @return Integer
     */
     Integer updateJobLevelById(Joblevel jobLevel);

    /**
     * 删除 id
     * @param id id
     * @return Integer
     */
     Integer deleteJobLevelById(Integer id);

    /**
     * 删除ids
     * @param ids array
     * @return Integer
     */
     Integer deleteJobLevelsByIds(Integer[] ids);

}
