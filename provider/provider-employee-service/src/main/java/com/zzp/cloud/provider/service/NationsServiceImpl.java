package com.zzp.cloud.provider.service;

import com.zzp.cloud.common.dto.cms.NationDto;
import com.zzp.cloud.provider.api.NationService;
import com.zzp.cloud.provider.domain.Nation;
import com.zzp.cloud.provider.mapper.NationMapper;
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
public class NationsServiceImpl implements NationService {
    @Resource
    private NationMapper nationMapper;

    @Override
    public List<Nation> getAllNations() {
        return nationMapper.selectAll();
    }

    @Override
    public List<NationDto> getAllNationDto(){
        return nationMapper
                .selectAll()
                .stream()
                .map(nation -> {
                    var n = new NationDto();
                    n.setId(nation.getId());
                    n.setName(nation.getName());
                    return n;
                }).collect(Collectors.toList());
    }

}
