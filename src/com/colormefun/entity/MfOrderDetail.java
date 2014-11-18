package com.colormefun.entity;

import shop.common.util.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by tong on 2014/7/22.
 */
@Entity
@Table(name = "mf_order_detail")
@IdClass(MfOrderDetailPK.class)
public class MfOrderDetail {
    private String orderNo;
    private int caseNo;
    private int lineNo;
    private Integer qty;
    private BigDecimal price;
//    private BigDecimal cuponePrice;
    private String priceStr;
    private String comment;

    private MfOrder order;
    private MfCase mfCase;


    @Id
    @Column(name = "order_no")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Id
    @Column(name = "case_no")
    public int getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(int caseNo) {
        this.caseNo = caseNo;
    }

    @Basic
    @Column(name = "line_no")
    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    @Basic
    @Column(name = "qty")
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_no")
    public MfOrder getOrder() {
        return order;
    }

    public void setOrder(MfOrder order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "case_no")
    public MfCase getMfCase() {
        return mfCase;
    }

    public void setMfCase(MfCase mfCase) {
        this.mfCase = mfCase;
    }

    @Transient
    public String getPriceStr() {
        if(null == priceStr){
            priceStr = StringUtils.fmtMoney(price);
        }
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfOrderDetail that = (MfOrderDetail) o;

        if (caseNo != that.caseNo) return false;
        if (lineNo != that.lineNo) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (orderNo != null ? !orderNo.equals(that.orderNo) : that.orderNo != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (qty != null ? !qty.equals(that.qty) : that.qty != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderNo != null ? orderNo.hashCode() : 0;
        result = 31 * result + caseNo;
        result = 31 * result + lineNo;
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
