package com.zzp.cloud.provider.service;

import com.zzp.cloud.common.dto.cms.PositionDto;
import com.zzp.cloud.provider.api.PositionService;
import com.zzp.cloud.provider.domain.Position;
import com.zzp.cloud.provider.mapper.PositionMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * <p>
 *  //TODO
 *  PositionServiceImpl.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 23:10
 * @see  com.zzp.cloud.provider.service
 **/
@DubboService(version = "0.0.1", delay = -1,timeout = 6000)
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionMapper positionMapper;


    @Override
    public List<Position> getAllPositions() {
        return positionMapper.selectAll();
    }

    @Override
    public List<PositionDto> getAllPositionDto() {
        return positionMapper.selectAll()
                .stream()
                .map(p->{
                    var dto = new PositionDto();
                    dto.setId(p.getId());
                    dto.setName(p.getName());
                    dto.setCreateDate(p.getCreatedate());
                    dto.setEnabled(p.getEnabled());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public Integer addPosition(Position position) {
        position.setCreatedate(new Date());
        position.setEnabled(true);
        return positionMapper.insertSelective(position);
    }

    @Override
    public Integer updatePositions(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    @Override
    public Integer deletePositionById(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deletePositionsByIds(Integer[] ids) {
        return positionMapper.deletePositionsByIds(ids);
    }
}
