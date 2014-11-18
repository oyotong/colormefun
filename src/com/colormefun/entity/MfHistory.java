package com.colormefun.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by tong-note on 2014/8/21.
 */
@Entity
@Table(name = "mf_history")
public class MfHistory {
    private String orderNo;
    private Integer userNo;
    private String comment;
    private String status;
    private String active;
    private Timestamp createdDate;

    @Id
    @Column(name = "order_no")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "user_no")
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "active")
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfHistory mfHistory = (MfHistory) o;

        if (active != null ? !active.equals(mfHistory.active) : mfHistory.active != null) return false;
        if (comment != null ? !comment.equals(mfHistory.comment) : mfHistory.comment != null) return false;
        if (createdDate != null ? !createdDate.equals(mfHistory.createdDate) : mfHistory.createdDate != null)
            return false;
        if (orderNo != null ? !orderNo.equals(mfHistory.orderNo) : mfHistory.orderNo != null) return false;
        if (status != null ? !status.equals(mfHistory.status) : mfHistory.status != null) return false;
        if (userNo != null ? !userNo.equals(mfHistory.userNo) : mfHistory.userNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderNo != null ? orderNo.hashCode() : 0;
        result = 31 * result + (userNo != null ? userNo.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
