package com.zzp.cloud.provider.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
/**
 *
 * <p>
 *  //TODO
 *  Salary.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/5 1:05
 * @see  com.zzp.cloud.provider.domain
 **/
@Data
@Table(name = "vhr.salary")
public class Salary implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 基本工资
     */
    @Column(name = "basicSalary")
    private Integer basicSalary;

    /**
     * 奖金
     */
    @Column(name = "bonus")
    private Integer bonus;

    /**
     * 午餐补助
     */
    @Column(name = "lunchSalary")
    private Integer lunchSalary;

    /**
     * 交通补助
     */
    @Column(name = "trafficSalary")
    private Integer trafficSalary;

    /**
     * 应发工资
     */
    @Column(name = "allSalary")
    private Integer allSalary;

    /**
     * 养老金基数
     */
    @Column(name = "pensionBase")
    private Integer pensionBase;

    /**
     * 养老金比率
     */
    @Column(name = "pensionPer")
    private Double pensionPer;

    /**
     * 启用时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "createDate")
    private Date createDate;

    /**
     * 医疗基数
     */
    @Column(name = "medicalBase")
    private Integer medicalBase;

    /**
     * 医疗保险比率
     */
    @Column(name = "medicalPer")
    private Double medicalPer;

    /**
     * 公积金基数
     */
    @Column(name = "accumulationFundBase")
    private Integer accumulationFundBase;

    /**
     * 公积金比率
     */
    @Column(name = "accumulationFundPer")
    private Double accumulationFundPer;

    @Column(name = "`name`")
    private String name;

    private static final long serialVersionUID = 5205646935155539864L;
}