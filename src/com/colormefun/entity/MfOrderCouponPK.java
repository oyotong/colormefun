package com.colormefun.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by tong on 2014/7/22.
 */
public class MfOrderCouponPK implements Serializable {
    private int couponId;
    private String orderNo;

    @Column(name = "coupon_id")
    @Id
    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    @Column(name = "order_no")
    @Id
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfOrderCouponPK that = (MfOrderCouponPK) o;

        if (couponId != that.couponId) return false;
        if (orderNo != null ? !orderNo.equals(that.orderNo) : that.orderNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = couponId;
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        return result;
    }
}
