package com.zzp.cloud.provider.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * HrRole 连接
 * <p>
 * //TODO
 * HrRole.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/28 21:17
 * @see com.zzp.cloud.provider.domain
 **/
@Data
@Table(name = "vhr.hr_role")
public class HrRole implements Serializable {
    private static final long serialVersionUID = -6157371015213631843L;
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "hrid")
    private Integer hrid;

    @Column(name = "rid")
    private Integer rid;

    public HrRole(Integer hrid, Integer rid) {
        this.hrid = hrid;
        this.rid = rid;
    }


}