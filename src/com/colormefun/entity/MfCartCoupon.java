package com.colormefun.entity;

import javax.persistence.*;

/**
 * Created by tong on 2014/7/22.
 */
@Entity
@Table(name = "mf_cart_coupon")
@IdClass(MfCartCouponPK.class)
public class MfCartCoupon {
    private int cartNo;
    private int couponId;

    @Id
    @Column(name = "cart_no")
    public int getCartNo() {
        return cartNo;
    }

    public void setCartNo(int cartNo) {
        this.cartNo = cartNo;
    }

    @Id
    @Column(name = "coupon_id")
    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfCartCoupon that = (MfCartCoupon) o;

        if (cartNo != that.cartNo) return false;
        if (couponId != that.couponId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartNo;
        result = 31 * result + couponId;
        return result;
    }
}
