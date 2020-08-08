package com.zzp.cloud.provider.mapper;

import com.zzp.cloud.common.dto.emp.HrDto;
import com.zzp.cloud.provider.domain.Hr;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.CommonMapper;

import java.util.List;

/**
 * HrMapper
 * <p>
 * //TODO
 * HrMapper.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/27 19:50
 * @see HrMapper
 **/
public interface HrMapper extends CommonMapper<Hr> {
    /**
     * 更新
     *
     * @param hrId    .
     * @param newPass .
     * @return .
     */
    @Update({"update hr set password = #{newPass} where id=#{hrId};"})
    Integer updatePasswd(@Param("hrId") Integer hrId, @Param("newPass") String newPass);

    /**
     * 更加关键词和查除了自己的hr
     *
     * @param id       .
     * @param keywords .
     * @return .
     */
    List<HrDto> findAllHrs(@Param("hrid") Integer id, @Param("keywords") String keywords);

}