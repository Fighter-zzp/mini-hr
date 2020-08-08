package com.zzp.cloud.common.dto;

import java.util.List;
/**
 * 含分页数据的统一返回
 * <p>
 *  //TODO
 *  RespPageBean.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 17:41
 * @see  com.zzp.cloud.common.dto
 **/
public class RespPageBean<T> {
    private Long total;
    private List<T> data;

    public Long getTotal() {
        return total;
    }

    public RespPageBean<T> setTotal(Long total) {
        this.total = total;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public RespPageBean<T> setData(List<T> data) {
        this.data = data;
        return this;
    }
}
