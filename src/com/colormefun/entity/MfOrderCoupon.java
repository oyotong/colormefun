package com.colormefun.entity;

import javax.persistence.*;

/**
 * Created by tong on 2014/7/22.
 */
@Entity
@Table(name = "mf_order_coupon")
@IdClass(MfOrderCouponPK.class)
public class MfOrderCoupon {
    private int couponId;
    private String orderNo;

    private MfCoupon coupon;
    private MfOrder order;

    @Id
    @Column(name = "coupon_id")
    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    @Id
    @Column(name = "order_no")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @ManyToOne()
    @JoinColumn(name = "coupon_id")
    public MfCoupon getCoupon() {
        return coupon;
    }

    public void setCoupon(MfCoupon coupon) {
        this.coupon = coupon;
    }

    @ManyToOne()
    @JoinColumn(name = "order_no")
    public MfOrder getOrder() {
        return order;
    }

    public void setOrder(MfOrder order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfOrderCoupon that = (MfOrderCoupon) o;

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
