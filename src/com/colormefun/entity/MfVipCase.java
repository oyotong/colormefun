package com.colormefun.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tong on 2014/7/22.
 */
/*
CREATE TABLE mf_vip_case
(
    case_no INT PRIMARY KEY NOT NULL,
    case_type INT NOT NULL,
    company_name VARCHAR(50),
    region VARCHAR(20),
    area VARCHAR(20),
    contact_name VARCHAR(50),
    contact_phone VARCHAR(20),
    contact_email VARCHAR(50),
    member_num INT,
    kids_num INT,
    need_location VARCHAR(10),
    location VARCHAR(200),
    description VARCHAR(500),
    status VARCHAR(10),
    active VARCHAR(4),
    created_date DATETIME
);

 */
@Entity
@Table(name = "mf_vip_case")
public class MfVipCase {
    private Integer caseNo;
    private String caseType;
    private String companyName;
    private String region;
    private String area;
    private String contactName;
    private String contactPhone;
    private String email;
    private Integer memberNum;
    private Integer memberNum2;
    private Integer kidsNum;
    private Integer kidsNum2;
    private String needLocation;
    private String location;
    private String description;
    private String status;
    private String active;
    private Date createdDate;
    private Date createdDate2;
    private String caseKey;

    public static enum CaseType{
        vip, ent_vip
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "case_no")
    public Integer getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(Integer caseNo) {
        this.caseNo = caseNo;
    }


    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "active")
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Basic
    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Transient
    public Date getCreatedDate2() {
        return createdDate2;
    }

    public void setCreatedDate2(Date createdDate2) {
        this.createdDate2 = createdDate2;
    }

    @Basic
    @Column(name = "case_type")
    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "region")
    public String getRegion() {
        return region;
    }


    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "contact_name")
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Basic
    @Column(name = "contact_phone")
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Basic
    @Column(name = "contact_email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "member_num")
    public Integer getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    @Basic
    @Column(name = "kids_num")
    public Integer getKidsNum() {
        return kidsNum;
    }

    public void setKidsNum(Integer kidsNum) {
        this.kidsNum = kidsNum;
    }

    @Basic
    @Column(name = "need_location")
    public String getNeedLocation() {
        return needLocation;
    }

    public void setNeedLocation(String needLocation) {
        this.needLocation = needLocation;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Transient
    public Integer getMemberNum2() {
        return memberNum2;
    }

    public void setMemberNum2(Integer memberNum2) {
        this.memberNum2 = memberNum2;
    }

    @Transient
    public Integer getKidsNum2() {
        return kidsNum2;
    }

    public void setKidsNum2(Integer kidsNum2) {
        this.kidsNum2 = kidsNum2;
    }

    @Basic
    @Column(name = "case_key")
    public String getCaseKey() {
        return caseKey;
    }

    public void setCaseKey(String caseKey) {
        this.caseKey = caseKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfVipCase mfVipCase = (MfVipCase) o;

        if (active != null ? !active.equals(mfVipCase.active) : mfVipCase.active != null) return false;
        if (area != null ? !area.equals(mfVipCase.area) : mfVipCase.area != null) return false;
        if (caseNo != null ? !caseNo.equals(mfVipCase.caseNo) : mfVipCase.caseNo != null) return false;
        if (caseType != null ? !caseType.equals(mfVipCase.caseType) : mfVipCase.caseType != null) return false;
        if (companyName != null ? !companyName.equals(mfVipCase.companyName) : mfVipCase.companyName != null)
            return false;
        if (email != null ? !email.equals(mfVipCase.email) : mfVipCase.email != null)
            return false;
        if (contactName != null ? !contactName.equals(mfVipCase.contactName) : mfVipCase.contactName != null)
            return false;
        if (contactPhone != null ? !contactPhone.equals(mfVipCase.contactPhone) : mfVipCase.contactPhone != null)
            return false;
        if (createdDate != null ? !createdDate.equals(mfVipCase.createdDate) : mfVipCase.createdDate != null)
            return false;
        if (description != null ? !description.equals(mfVipCase.description) : mfVipCase.description != null)
            return false;
        if (kidsNum != null ? !kidsNum.equals(mfVipCase.kidsNum) : mfVipCase.kidsNum != null) return false;
        if (location != null ? !location.equals(mfVipCase.location) : mfVipCase.location != null) return false;
        if (memberNum != null ? !memberNum.equals(mfVipCase.memberNum) : mfVipCase.memberNum != null) return false;
        if (needLocation != null ? !needLocation.equals(mfVipCase.needLocation) : mfVipCase.needLocation != null)
            return false;
        if (region != null ? !region.equals(mfVipCase.region) : mfVipCase.region != null) return false;
        if (status != null ? !status.equals(mfVipCase.status) : mfVipCase.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = caseNo != null ? caseNo.hashCode() : 0;
        result = 31 * result + (caseType != null ? caseType.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (contactName != null ? contactName.hashCode() : 0);
        result = 31 * result + (contactPhone != null ? contactPhone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (memberNum != null ? memberNum.hashCode() : 0);
        result = 31 * result + (kidsNum != null ? kidsNum.hashCode() : 0);
        result = 31 * result + (needLocation != null ? needLocation.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
