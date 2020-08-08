package com.zzp.cloud.provider.mapper;

import com.zzp.cloud.provider.domain.Joblevel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.CommonMapper;

/**
 * JoblevelMapper
 * <p>
 *  //TODO
 *  JoblevelMapper.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/4 20:54
 * @see  com.zzp.cloud.provider.mapper
 **/
public interface JoblevelMapper extends CommonMapper<Joblevel> {
    /**
     * 删除多个
     * @param ids arrays
     * @return Integer
     */
    Integer deleteJobLevelsByIds(@Param("ids") Integer[] ids);
}