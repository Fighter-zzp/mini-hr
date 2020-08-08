package com.zzp.cloud.common.dto.cms;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 工作传输类
 * <p>
 * //TODO
 * Salary.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/2 18:48
 * @see com.zzp.cloud.common.dto.cms
 **/
public class SalaryDto {
    private Integer id;
    /**
     * 基础工资
     */
    private Integer basicSalary;
    /**
     * 奖金
     */
    private Integer bonus;
    /**
     * 午餐补贴
     */
    private Integer lunchSalary;
    /**
     * 交通补贴
     */
    private Integer trafficSalary;
    /**
     * 总工资
     */
    private Integer allSalary;
    /**
     * 养老金基数
     */
    private Integer pensionBase;
    /**
     * 养老金比例
     */
    private Float pensionPer;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    /**
     * 医疗金基数
     */
    private Integer medicalBase;
    /**
     * 医疗金比率
     */
    private Float medicalPer;
    /**
     * 公积金基数
     */
    private Integer accumulationFundBase;
    /**
     * 公积金比率
     */
    private Float accumulationFundPer;
    /**
     * 账本名
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Integer basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Integer getLunchSalary() {
        return lunchSalary;
    }

    public void setLunchSalary(Integer lunchSalary) {
        this.lunchSalary = lunchSalary;
    }

    public Integer getTrafficSalary() {
        return trafficSalary;
    }

    public void setTrafficSalary(Integer trafficSalary) {
        this.trafficSalary = trafficSalary;
    }

    public Integer getAllSalary() {
        return allSalary;
    }

    public void setAllSalary(Integer allSalary) {
        this.allSalary = allSalary;
    }

    public Integer getPensionBase() {
        return pensionBase;
    }

    public void setPensionBase(Integer pensionBase) {
        this.pensionBase = pensionBase;
    }

    public Float getPensionPer() {
        return pensionPer;
    }

    public void setPensionPer(Float pensionPer) {
        this.pensionPer = pensionPer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getMedicalBase() {
        return medicalBase;
    }

    public void setMedicalBase(Integer medicalBase) {
        this.medicalBase = medicalBase;
    }

    public Float getMedicalPer() {
        return medicalPer;
    }

    public void setMedicalPer(Float medicalPer) {
        this.medicalPer = medicalPer;
    }

    public Integer getAccumulationFundBase() {
        return accumulationFundBase;
    }

    public void setAccumulationFundBase(Integer accumulationFundBase) {
        this.accumulationFundBase = accumulationFundBase;
    }

    public Float getAccumulationFundPer() {
        return accumulationFundPer;
    }

    public void setAccumulationFundPer(Float accumulationFundPer) {
        this.accumulationFundPer = accumulationFundPer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}