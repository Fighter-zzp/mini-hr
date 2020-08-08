package com.zzp.cloud.provider.service;

import com.zzp.cloud.common.dto.cms.JobLevelDto;
import com.zzp.cloud.provider.api.JobLevelService;
import com.zzp.cloud.provider.domain.Joblevel;
import com.zzp.cloud.provider.mapper.JoblevelMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JobLevelServiceImpl
 * <p>
 * //TODO
 * JobLevelServiceImpl.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/2 20:19
 * @see com.zzp.cloud.provider.service
 **/
@DubboService(version = "0.0.1", delay = -1, timeout = 6000, retries = 5)
public class JobLevelServiceImpl implements JobLevelService {

    @Resource
    private JoblevelMapper joblevelMapper;

    @Override
    public List<Joblevel> getAllJobLevels() {
        return joblevelMapper.selectAll();
    }

    @Override
    public List<JobLevelDto> getAllJobLevelDto() {
        return joblevelMapper.selectAll()
                .stream()
                .map(j -> {
                    var jd = new JobLevelDto();
                    jd.setId(j.getId());
                    jd.setName(j.getName());
                    jd.setTitleLevel((String) j.getTitlelevel());
                    jd.setCreateDate(j.getCreatedate());
                    jd.setEnabled(j.getEnabled());
                    return jd;
                }).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer addJobLevel(Joblevel jobLevel) {
        jobLevel.setCreatedate(new Date());
        jobLevel.setEnabled(true);
        return joblevelMapper.insertSelective(jobLevel);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateJobLevelById(Joblevel jobLevel) {
        return joblevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteJobLevelById(Integer id) {
        return joblevelMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteJobLevelsByIds(Integer[] ids) {
        return joblevelMapper.deleteJobLevelsByIds(ids);
    }
}
