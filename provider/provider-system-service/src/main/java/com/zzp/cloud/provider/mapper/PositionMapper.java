package com.zzp.cloud.provider.mapper;

import com.zzp.cloud.provider.domain.Position;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.CommonMapper;
/**
 *
 * <p>
 *  //TODO
 *  PositionMapper.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 23:12
 * @see  com.zzp.cloud.provider.mapper
 **/
public interface PositionMapper extends CommonMapper<Position> {
    /**
     * 删除
     * @param ids .
     * @return .
     */
    Integer deletePositionsByIds(@Param("ids") Integer[] ids);
}