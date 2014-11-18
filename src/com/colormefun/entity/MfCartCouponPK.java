package com.colormefun.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by tong on 2014/7/22.
 */
public class MfCartCouponPK implements Serializable {
    private int cartNo;
    private int couponId;

    public MfCartCouponPK() {
    }

    public MfCartCouponPK(int cartNo, int couponId) {
        this.cartNo = cartNo;
        this.couponId = couponId;
    }

    @Column(name = "cart_no")
    @Id
    public int getCartNo() {
        return cartNo;
    }

    public void setCartNo(int cartNo) {
        this.cartNo = cartNo;
    }

    @Column(name = "coupon_id")
    @Id
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

        MfCartCouponPK that = (MfCartCouponPK) o;

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
