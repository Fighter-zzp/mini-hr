package com.zzp.cloud.provider.api;

import com.zzp.cloud.common.dto.cms.PoliticsStatusDto;
import com.zzp.cloud.provider.domain.Politicsstatus;

import java.util.List;

/**
 * PoliticsStatusService
 * <p>
 *  //TODO
 *  PoliticsStatusService.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 20:30
 * @see  com.zzp.cloud.provider.api
 **/
public interface PoliticsStatusService {
    /**
     * 获取全部政治状态
     * @return .
     */
    List<Politicsstatus> getAllPoliticsStatus();

    List<PoliticsStatusDto> getAllPoliticsStatusDto();
}
