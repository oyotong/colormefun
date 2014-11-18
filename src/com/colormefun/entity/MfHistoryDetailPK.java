package com.colormefun.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by tong-note on 2014/8/21.
 */
public class MfHistoryDetailPK implements Serializable {
    private String orderNo;
    private int caseNo;

    @Column(name = "order_no")
    @Id
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

        MfHistoryDetailPK that = (MfHistoryDetailPK) o;

        if (caseNo != that.caseNo) return false;
        if (orderNo != null ? !orderNo.equals(that.orderNo) : that.orderNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderNo != null ? orderNo.hashCode() : 0;
        result = 31 * result + caseNo;
        return result;
    }
}
