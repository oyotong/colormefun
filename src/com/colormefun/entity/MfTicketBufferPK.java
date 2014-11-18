package com.colormefun.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by tong-note on 2014/9/24.
 */
public class MfTicketBufferPK implements Serializable {



    private int caseNo;
    private int indexNo;

    public MfTicketBufferPK(){}

    public MfTicketBufferPK(Integer caseNo, Integer indexNo) {
        this.caseNo = caseNo;
        this.indexNo = indexNo;
    }

    @Column(name = "case_no")
    @Id
    public int getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(int caseNo) {
        this.caseNo = caseNo;
    }

    @Column(name = "index_no")
    @Id
    public int getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(int indexNo) {
        this.indexNo = indexNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfTicketBufferPK that = (MfTicketBufferPK) o;

        if (caseNo != that.caseNo) return false;
        if (indexNo != that.indexNo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = caseNo;
        result = 31 * result + indexNo;
        return result;
    }
}
