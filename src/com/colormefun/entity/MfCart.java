package com.colormefun.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tong on 2014/7/22.
 */
@Entity
@Table(name = "mf_cart")
public class MfCart {
    private Integer cartNo;
//    private Integer userNo;
//    private Integer caseNo;
    private String active;
    private Integer qty;
    private Date orderedDate;
    private Date createdDate;

    private MfUser user;
    private MfCase mfCase;

    @Id
    @Column(name = "cart_no")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getCartNo() {
        return cartNo;
    }

    public void setCartNo(Integer cartNo) {
        this.cartNo = cartNo;
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
    @Column(name = "qty")
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
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
    @Column(name = "ordered_date")
    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    @Basic
    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MfUser.class)
    @JoinColumn(name = "user_no")
    public MfUser getUser() {
        return user;
    }

    public void setUser(MfUser user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MfCase.class)
    @JoinColumn(name = "case_no")
    public MfCase getMfCase() {
        return mfCase;
    }

    public void setMfCase(MfCase mfCase) {
        this.mfCase = mfCase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfCart mfCart = (MfCart) o;

        if (cartNo != mfCart.cartNo) return false;
        if (active != null ? !active.equals(mfCart.active) : mfCart.active != null) return false;
        if (createdDate != null ? !createdDate.equals(mfCart.createdDate) : mfCart.createdDate != null) return false;
        if (orderedDate != null ? !orderedDate.equals(mfCart.orderedDate) : mfCart.orderedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartNo;
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (orderedDate != null ? orderedDate.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
