package com.zzp.cloud.provider.api;

import com.zzp.cloud.common.dto.cms.PositionDto;
import com.zzp.cloud.provider.domain.Position;

import java.util.List;

/**
 * 职位
 * <p>
 * //TODO
 * PositionService.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/2 23:03
 * @see com.zzp.cloud.provider.api
 **/
public interface PositionService {
    /**
     * 获取所有职位
     * @return .
     */
    List<Position> getAllPositions();

    /**
     * 获取dtos
     * @return .
     */
    List<PositionDto> getAllPositionDto();

    /**
     * 添加
     * @param position .
     * @return .
     */
    Integer addPosition(Position position);

    /**
     * 更新
     * @param position .
     * @return .
     */
    Integer updatePositions(Position position);

    /**
     * 删除
     * @param id .
     * @return .
     */
    Integer deletePositionById(Integer id);

    /**
     * 删除
     * @param ids .
     * @return .
     */
    Integer deletePositionsByIds(Integer[] ids);

}
