package com.zzp.cloud.provider.api;

import com.zzp.cloud.common.dto.cms.NationDto;
import com.zzp.cloud.provider.domain.Nation;

import java.util.List;

/**
 * NationService
 * <p>
 *  //TODO
 *  NationService.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 20:27
 * @see  com.zzp.cloud.provider.api
 **/
public interface NationService {
    /**
     * 获取全部族
     * @return .
     */
    List<Nation> getAllNations();

    List<NationDto> getAllNationDto();
}
