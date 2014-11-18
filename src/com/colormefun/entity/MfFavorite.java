package com.colormefun.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by tong on 2014/7/22.
 */
@Entity
@Table(name = "mf_favorite")
public class MfFavorite {
    private int favoriteId;
//    private int caseNo;
    private MfCase mfCase;
//    private int userNo;
    private MfUser user;
    private String comment;
    private String active;
    private Date deletedDate;
    private Date createdDate;
    private Date createdDate2;

    @Id
    @Column(name = "favorite_id")
    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

//    @Basic
//    @Column(name = "case_no")
//    public int getCaseNo() {
//        return caseNo;
//    }
//
//    public void setCaseNo(int caseNo) {
//        this.caseNo = caseNo;
//    }
//
//    @Basic
//    @Column(name = "user_no")
//    public int getUserNo() {
//        return userNo;
//    }
//
//    public void setUserNo(int userNo) {
//        this.userNo = userNo;
//    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
    @Column(name = "deleted_date")
    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Basic
    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_no")
    public MfUser getUser() {
        return user;
    }

    public void setUser(MfUser user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="case_no")
    public MfCase getMfCase() {
        return mfCase;
    }

    public void setMfCase(MfCase mfCase) {
        this.mfCase = mfCase;
    }

    @Transient
    public Date getCreatedDate2() {
        return createdDate2;
    }

    public void setCreatedDate2(Date createdDate2) {
        this.createdDate2 = createdDate2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfFavorite that = (MfFavorite) o;

//        if (caseNo != that.caseNo) return false;
//        if (favoriteId != that.favoriteId) return false;
//        if (userNo != that.userNo) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (deletedDate != null ? !deletedDate.equals(that.deletedDate) : that.deletedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = favoriteId;
//        result = 31 * result + caseNo;
//        result = 31 * result + userNo;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (deletedDate != null ? deletedDate.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
