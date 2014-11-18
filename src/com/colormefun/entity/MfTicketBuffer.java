package com.colormefun.entity;

import javax.persistence.*;

/**
 * Created by tong-note on 2014/9/24.
 */
@Entity
@Table(name = "mf_ticket_buffer", schema = "", catalog = "a0812133843")
@IdClass(MfTicketBufferPK.class)
public class MfTicketBuffer {
    private Integer caseNo;
    private Integer indexNo;
    private MfCase mfCase;
    private String email;
    private String mobilePhone;
    private java.util.Date createdDate;
    private String createdId;
    private Integer qty;
    private String comments;

    @Id
    @Column(name = "case_no")
    public Integer getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(Integer caseNo) {
        this.caseNo = caseNo;
    }

    @Id
    @Column(name = "index_no")
    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "mobile_phone")
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "created_date")
    public java.util.Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "created_id")
    public String getCreatedId() {
        return createdId;
    }

    public void setCreatedId(String createdId) {
        this.createdId = createdId;
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
    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Transient
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

        MfTicketBuffer that = (MfTicketBuffer) o;

        if (caseNo != that.caseNo) return false;
        if (indexNo != that.indexNo) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdId != null ? !createdId.equals(that.createdId) : that.createdId != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = caseNo;
        result = 31 * result + indexNo;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdId != null ? createdId.hashCode() : 0);
        return result;
    }
}
