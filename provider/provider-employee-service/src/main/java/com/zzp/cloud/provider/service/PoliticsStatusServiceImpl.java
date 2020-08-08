package com.zzp.cloud.provider.service;

import com.zzp.cloud.common.dto.cms.PoliticsStatusDto;
import com.zzp.cloud.provider.api.PoliticsStatusService;
import com.zzp.cloud.provider.domain.Politicsstatus;
import com.zzp.cloud.provider.mapper.PoliticsstatusMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * NationsServiceImpl
 * <p>
 *  //TODO
 *  NationsServiceImpl.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 20:29
 * @see  com.zzp.cloud.provider.service
 **/
@DubboService(version = "0.0.1", delay = -1, timeout = 6000, retries = 5)
public class PoliticsStatusServiceImpl implements PoliticsStatusService {
    @Resource
    private PoliticsstatusMapper politicsstatusMapper;


    @Override
    public List<Politicsstatus> getAllPoliticsStatus() {
        return politicsstatusMapper.selectAll();
    }

    @Override
    public List<PoliticsStatusDto> getAllPoliticsStatusDto() {
        return politicsstatusMapper.selectAll()
                .stream().map(ps -> {
                    var psd = new PoliticsStatusDto();
                    psd.setId(ps.getId());
                    psd.setName(ps.getName());
                    return psd;
                }).collect(Collectors.toList());
    }
}
