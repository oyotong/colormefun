package com.colormefun.entity;

import shop.company.entity.AuthPurview;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created by tong on 2014/7/22.
 */
@Entity
@Table(name = "mf_order")
public class MfOrder {

    private String orderNo;
//    private Integer userNo;
    private String comment;
    private String status;
    private String active;
    private Date createdDate;
    private Date createdDate2;
    private String userName;

    private BigDecimal summary;
    private BigDecimal summary2;
    private Set<MfOrderDetail> details;

    private BigDecimal couponSummary;
    private BigDecimal couponSummary2;
    private Set<MfCoupon> coupons;
    private MfUser user;

    public static enum OrderStatus{
        created,paid,closed,deleted,archived
    }

    @Id
    @Column(name = "order_no")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

//    @Basic
//    @Column(name = "user_no")
//    public Integer getUserNo() {
//        return userNo;
//    }
//
//    public void setUserNo(Integer userNo) {
//        this.userNo = userNo;
//    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Transient
    public BigDecimal getCouponSummary() {
        if(couponSummary != null){
            return couponSummary;
        }
        BigDecimal subTotal = new BigDecimal(0);
        if(null != coupons){
            for(MfCoupon coupon: coupons){
                subTotal = subTotal.add(coupon.getDeduction());
            }
        }
        return couponSummary = subTotal;
    }

    @Transient
    public BigDecimal getSummary() {
        if(summary != null){
            return summary;
        }
        BigDecimal subTotal = new BigDecimal(0);
        if(null != details){
            for(MfOrderDetail detail: details){
                subTotal = subTotal.add(detail.getPrice().multiply(new BigDecimal(detail.getQty())));
            }
        }
        return summary = subTotal;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.EAGER, mappedBy = "order")
    public Set<MfOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<MfOrderDetail> details) {
        this.details = details;
    }

    @ManyToMany(fetch = FetchType.EAGER,targetEntity=MfCoupon.class)
    @JoinTable(name = "mf_order_coupon", joinColumns = { @JoinColumn(name = "order_no", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "coupon_id", nullable = false, updatable = false) })
    public Set<MfCoupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(Set<MfCoupon> orderCoupons) {
        this.coupons = orderCoupons;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_no")
    public MfUser getUser() {
        return user;
    }

    public void setUser(MfUser user) {
        this.user = user;
    }

    @Transient
    public Date getCreatedDate2() {
        return createdDate2;
    }

    public void setCreatedDate2(Date createdDate2) {
        this.createdDate2 = createdDate2;
    }

    @Transient
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSummary(BigDecimal summary) {
        this.summary = summary;
    }

    public void setCouponSummary(BigDecimal couponSummary) {
        this.couponSummary = couponSummary;
    }

    @Transient
    public BigDecimal getSummary2() {
        return summary2;
    }

    public void setSummary2(BigDecimal summary2) {
        this.summary2 = summary2;
    }

    @Transient
    public BigDecimal getCouponSummary2() {
        return couponSummary2;
    }

    public void setCouponSummary2(BigDecimal couponSummary2) {
        this.couponSummary2 = couponSummary2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfOrder mfOrder = (MfOrder) o;

        if (active != null ? !active.equals(mfOrder.active) : mfOrder.active != null) return false;
        if (comment != null ? !comment.equals(mfOrder.comment) : mfOrder.comment != null) return false;
        if (createdDate != null ? !createdDate.equals(mfOrder.createdDate) : mfOrder.createdDate != null) return false;
        if (orderNo != null ? !orderNo.equals(mfOrder.orderNo) : mfOrder.orderNo != null) return false;
        if (status != null ? !status.equals(mfOrder.status) : mfOrder.status != null) return false;
//        if (userNo != null ? !userNo.equals(mfOrder.userNo) : mfOrder.userNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderNo != null ? orderNo.hashCode() : 0;
//        result = 31 * result + (userNo != null ? userNo.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
