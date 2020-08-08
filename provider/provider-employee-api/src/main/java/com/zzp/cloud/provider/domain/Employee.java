package com.zzp.cloud.provider.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
/**
 * 员工映射类
 * <p>
 *  //TODO
 *  Employee.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 18:09
 * @see  com.zzp.cloud.provider.domain
 **/
@Data
@Table(name = "vhr.employee")
public class Employee implements Serializable {
    private static final long serialVersionUID = -984124614886491389L;
    /**
     * 员工编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 员工姓名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 性别
     */
    @Column(name = "gender")
    private String gender;

    /**
     * 出生日期
     */
    @Column(name = "birthday")
    private Date birthday;

    /**
     * 身份证号
     */
    @Column(name = "idCard")
    private String idcard;

    /**
     * 婚姻状况
     */
    @Column(name = "wedlock")
    private Object wedlock;

    /**
     * 民族
     */
    @Column(name = "nationId")
    private Integer nationid;

    /**
     * 籍贯
     */
    @Column(name = "nativePlace")
    private String nativeplace;

    /**
     * 政治面貌
     */
    @Column(name = "politicId")
    private Integer politicid;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 电话号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 联系地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 所属部门
     */
    @Column(name = "departmentId")
    private Integer departmentid;

    /**
     * 职称ID
     */
    @Column(name = "jobLevelId")
    private Integer joblevelid;

    /**
     * 职位ID
     */
    @Column(name = "posId")
    private Integer posid;

    /**
     * 聘用形式
     */
    @Column(name = "engageForm")
    private String engageform;

    /**
     * 最高学历
     */
    @Column(name = "tiptopDegree")
    private Object tiptopdegree;

    /**
     * 所属专业
     */
    @Column(name = "specialty")
    private String specialty;

    /**
     * 毕业院校
     */
    @Column(name = "school")
    private String school;

    /**
     * 入职日期
     */
    @Column(name = "beginDate")
    private Date begindate;

    /**
     * 在职状态
     */
    @Column(name = "workState")
    private Object workstate;

    /**
     * 工号
     */
    @Column(name = "workID")
    private String workid;

    /**
     * 合同期限
     */
    @Column(name = "contractTerm")
    private Double contractterm;

    /**
     * 转正日期
     */
    @Column(name = "conversionTime")
    private Date conversiontime;

    /**
     * 离职日期
     */
    @Column(name = "notWorkDate")
    private Date notworkdate;

    /**
     * 合同起始日期
     */
    @Column(name = "beginContract")
    private Date begincontract;

    /**
     * 合同终止日期
     */
    @Column(name = "endContract")
    private Date endcontract;

    /**
     * 工龄
     */
    @Column(name = "workAge")
    private Integer workage;
}