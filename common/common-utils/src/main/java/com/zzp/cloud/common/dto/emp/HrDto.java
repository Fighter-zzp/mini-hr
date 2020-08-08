package com.zzp.cloud.common.dto.emp;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * hr传输类
 * <p>
 *  //TODO
 *  HrDto.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/5 0:19
 * @see  com.zzp.cloud.common.dto.emp
 **/
@Data
public class HrDto {

    private Integer id;

    private String name;

    private Boolean enabled;

    private String username;
}
