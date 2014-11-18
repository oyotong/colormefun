package com.colormefun.entity;

import javax.persistence.*;

/**
 * Created by tong on 2014/7/22.
 */
@Entity
@Table(name = "mf_cart_detail")
@IdClass(MfCartDetailPK.class)
public class MfCartDetail {
    private int cartNo;
    private int caseNo;
    private Integer lineNo;
    private Integer qty;

    @Id
    @Column(name = "cart_no")
    public int getCartNo() {
        return cartNo;
    }

    public void setCartNo(int cartNo) {
        this.cartNo = cartNo;
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
    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfCartDetail that = (MfCartDetail) o;

        if (cartNo != that.cartNo) return false;
        if (caseNo != that.caseNo) return false;
        if (lineNo != null ? !lineNo.equals(that.lineNo) : that.lineNo != null) return false;
        if (qty != null ? !qty.equals(that.qty) : that.qty != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartNo;
        result = 31 * result + caseNo;
        result = 31 * result + (lineNo != null ? lineNo.hashCode() : 0);
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        return result;
    }
}
