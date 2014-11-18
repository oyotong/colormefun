package com.colormefun.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by tong on 2014/7/22.
 */
@Entity
@Table(name = "mf_coupon")
public class MfCoupon {

    private Integer couponId;
    private String couponNo;
    private String password;
    private Date deadline;
    private Date deadline2;
    private BigDecimal deduction;
    private BigDecimal deduction2;
    private BigDecimal discount;
    private BigDecimal discount2;
    private String description;
    private String couponType;
    private String status;
    private String active;
    private Integer userNo;
    private String createdId;
    private Date createdDate;
    private Date createdDate2;

    private String version;
    private Integer total;


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "coupon_id")
    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    @Basic
    @Column(name = "coupon_no")
    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        if(null!=couponNo){
            couponNo = couponNo.replaceAll("\\s","");
        }
        this.couponNo = couponNo;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "deadline")
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "deduction")
    public BigDecimal getDeduction() {
        return deduction;
    }

    public void setDeduction(BigDecimal deduction) {
        this.deduction = deduction;
    }

    @Basic
    @Column(name = "discount")
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
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
    @Column(name = "coupon_type")
    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Column(name = "user_no")
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    @Basic
    @Column(name = "created_id")
    public String getCreatedId() {
        return createdId;
    }

    public void setCreatedId(String createdId) {
        this.createdId = createdId;
    }

    @Basic
    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "total")
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Transient
    public Date getDeadline2() {
        return deadline2;
    }

    public void setDeadline2(Date deadline2) {
        this.deadline2 = deadline2;
    }

    @Transient
    public BigDecimal getDeduction2() {
        return deduction2;
    }

    public void setDeduction2(BigDecimal deduction2) {
        this.deduction2 = deduction2;
    }

    @Transient
    public BigDecimal getDiscount2() {
        return discount2;
    }

    public void setDiscount2(BigDecimal discount2) {
        this.discount2 = discount2;
    }

    @Transient
    public Date getCreatedDate2() {
        return createdDate2;
    }

    public void setCreatedDate2(Date createdDate2) {
        this.createdDate2 = createdDate2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfCoupon mfCoupon = (MfCoupon) o;

        if (couponId != null ? !couponId.equals(mfCoupon.couponId) : mfCoupon.couponId != null) return false;
        if (active != null ? !active.equals(mfCoupon.active) : mfCoupon.active != null) return false;
        if (couponNo != null ? !couponNo.equals(mfCoupon.couponNo) : mfCoupon.couponNo != null) return false;
        if (couponType != null ? !couponType.equals(mfCoupon.couponType) : mfCoupon.couponType != null) return false;
        if (createdDate != null ? !createdDate.equals(mfCoupon.createdDate) : mfCoupon.createdDate != null)
            return false;
        if (createdId != null ? !createdId.equals(mfCoupon.createdId) : mfCoupon.createdId != null) return false;
        if (deadline != null ? !deadline.equals(mfCoupon.deadline) : mfCoupon.deadline != null) return false;
        if (deduction != null ? !deduction.equals(mfCoupon.deduction) : mfCoupon.deduction != null) return false;
        if (description != null ? !description.equals(mfCoupon.description) : mfCoupon.description != null)
            return false;
        if (discount != null ? !discount.equals(mfCoupon.discount) : mfCoupon.discount != null) return false;
        if (password != null ? !password.equals(mfCoupon.password) : mfCoupon.password != null) return false;
        if (status != null ? !status.equals(mfCoupon.status) : mfCoupon.status != null) return false;
        if (userNo != null ? !userNo.equals(mfCoupon.userNo) : mfCoupon.userNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (couponId != null ? couponId.hashCode() : 0);;
        result = 31 * result + (couponNo != null ? couponNo.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        result = 31 * result + (deduction != null ? deduction.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (couponType != null ? couponType.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (userNo != null ? userNo.hashCode() : 0);
        result = 31 * result + (createdId != null ? createdId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
