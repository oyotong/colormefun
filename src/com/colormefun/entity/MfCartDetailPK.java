package com.colormefun.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by tong on 2014/7/22.
 */
public class MfCartDetailPK implements Serializable {
    private int cartNo;
    private int caseNo;

    public MfCartDetailPK() {
    }

    public MfCartDetailPK(int cartNo, int caseNo) {
        this.cartNo = cartNo;
        this.caseNo = caseNo;
    }

    @Column(name = "cart_no")
    @Id
    public int getCartNo() {
        return cartNo;
    }

    public void setCartNo(int cartNo) {
        this.cartNo = cartNo;
    }

    @Column(name = "case_no")
    @Id
    public int getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(int caseNo) {
        this.caseNo = caseNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfCartDetailPK that = (MfCartDetailPK) o;

        if (cartNo != that.cartNo) return false;
        if (caseNo != that.caseNo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartNo;
        result = 31 * result + caseNo;
        return result;
    }
}
