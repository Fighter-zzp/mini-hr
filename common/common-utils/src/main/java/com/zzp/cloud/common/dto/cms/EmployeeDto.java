package com.zzp.cloud.common.dto.cms;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
/**
 * 员工类传输类
 * <p>
 *  //TODO
 *  sss.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/2 18:32
 * @see  com.zzp.cloud.common.dto.cms
 **/
public class EmployeeDto implements Serializable {
    private static final long serialVersionUID = 3782037415882057004L;
    private Integer id;

    private String name;

    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date birthday;

    private String idCard;

    private String wedlock;

    private Integer nationId;

    private String nativePlace;

    private Integer politicId;

    private String email;

    private String phone;

    private String address;

    private Integer departmentId;

    private Integer jobLevelId;

    private Integer posId;

    private String engageForm;

    private String tiptopDegree;

    private String specialty;

    private String school;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date beginDate;

    private String workState;

    private String workId;

    private Double contractTerm;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date conversionTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date notWorkDate;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date beginContract;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date endContract;

    private Integer workAge;
    private NationDto nation;
    private PoliticsStatusDto politicsStatus;
    private DepartmentDto department;
    private JobLevelDto jobLevel;
    private PositionDto position;
    private SalaryDto salary;


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", idCard='" + idCard + '\'' +
                ", wedlock='" + wedlock + '\'' +
                ", nationId=" + nationId +
                ", nativePlace='" + nativePlace + '\'' +
                ", politicId=" + politicId +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", departmentId=" + departmentId +
                ", jobLevelId=" + jobLevelId +
                ", posId=" + posId +
                ", engageForm='" + engageForm + '\'' +
                ", tiptopDegree='" + tiptopDegree + '\'' +
                ", specialty='" + specialty + '\'' +
                ", school='" + school + '\'' +
                ", beginDate=" + beginDate +
                ", workState='" + workState + '\'' +
                ", workID='" + workId + '\'' +
                ", contractTerm=" + contractTerm +
                ", conversionTime=" + conversionTime +
                ", notWorkDate=" + notWorkDate +
                ", beginContract=" + beginContract +
                ", endContract=" + endContract +
                ", workAge=" + workAge +
                ", nation=" + nation +
                ", politicsstatus=" + politicsStatus +
                ", department=" + department +
                ", jobLevel=" + jobLevel +
                ", position=" + position +
                '}';
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public PoliticsStatusDto getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(PoliticsStatusDto politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getWedlock() {
        return wedlock;
    }

    public void setWedlock(String wedlock) {
        this.wedlock = wedlock;
    }

    public Integer getNationId() {
        return nationId;
    }

    public void setNationId(Integer nationId) {
        this.nationId = nationId;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public Integer getPoliticId() {
        return politicId;
    }

    public void setPoliticId(Integer politicId) {
        this.politicId = politicId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Integer jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public String getEngageForm() {
        return engageForm;
    }

    public void setEngageForm(String engageForm) {
        this.engageForm = engageForm;
    }

    public String getTiptopDegree() {
        return tiptopDegree;
    }

    public void setTiptopDegree(String tiptopDegree) {
        this.tiptopDegree = tiptopDegree;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public String getWorkState() {
        return workState;
    }

    public void setWorkState(String workState) {
        this.workState = workState;
    }


    public Double getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(Double contractTerm) {
        this.contractTerm = contractTerm;
    }

    public Date getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(Date conversionTime) {
        this.conversionTime = conversionTime;
    }

    public Date getNotWorkDate() {
        return notWorkDate;
    }

    public void setNotWorkDate(Date notWorkDate) {
        this.notWorkDate = notWorkDate;
    }

    public Date getBeginContract() {
        return beginContract;
    }

    public void setBeginContract(Date beginContract) {
        this.beginContract = beginContract;
    }

    public Date getEndContract() {
        return endContract;
    }

    public void setEndContract(Date endContract) {
        this.endContract = endContract;
    }

    public Integer getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Integer workAge) {
        this.workAge = workAge;
    }

    public NationDto getNation() {
        return nation;
    }

    public void setNation(NationDto nation) {
        this.nation = nation;
    }


    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    public JobLevelDto getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(JobLevelDto jobLevel) {
        this.jobLevel = jobLevel;
    }

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }

    public SalaryDto getSalary() {
        return salary;
    }

    public void setSalary(SalaryDto salary) {
        this.salary = salary;
    }
}
