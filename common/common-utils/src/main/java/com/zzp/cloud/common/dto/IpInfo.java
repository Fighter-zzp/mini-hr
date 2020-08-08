package com.zzp.cloud.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 佐斯特勒
 * <p>
 * 记录ip信息的类
 * </p>
 * @version v1.0.0
 * @date 2020/2/27 14:35
 * @see IpInfo
 **/
@Data
public class IpInfo implements Serializable {

    private static final long serialVersionUID = -5508711941465311258L;
    private String ip;
    private String country;
    private String area;
    private String region;
    private String city;
    private String county;
    private String isp;
    private String country_id;
    private String area_id;
    private String region_id;
    private String city_id;
    private String county_id;
    private String isp_id;
}
