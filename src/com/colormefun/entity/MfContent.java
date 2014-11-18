package com.colormefun.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tong on 2014/7/22.
 */
@Entity
@Table(name = "mf_content")
public class MfContent {
    private Integer id;
    private String title;
    private String subTitle;//url
    private String contentType;
    private String content;
    private String comment;
    private String active;
    private String createdId;
    private Date createdDate;
    private Date createdDate2;
    private String pic1;
    private Integer seqNo;

    public static enum ContentType{
        question, about, marquee
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "sub_title")
    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @Basic
    @Column(name = "content_type")
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "active")
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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
    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Transient
    public Date getCreatedDate2() {
        return createdDate2;
    }

    public void setCreatedDate2(Date createdDate2) {
        this.createdDate2 = createdDate2;
    }

    @Basic
    @Column(name = "pic1")
    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    @Basic
    @Column(name = "seq_no")
    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfContent mfContent = (MfContent) o;

        if (id != mfContent.id) return false;
        if (active != null ? !active.equals(mfContent.active) : mfContent.active != null) return false;
        if (comment != null ? !comment.equals(mfContent.comment) : mfContent.comment != null) return false;
        if (content != null ? !content.equals(mfContent.content) : mfContent.content != null) return false;
        if (contentType != null ? !contentType.equals(mfContent.contentType) : mfContent.contentType != null)
            return false;
        if (createdDate != null ? !createdDate.equals(mfContent.createdDate) : mfContent.createdDate != null)
            return false;
        if (createdId != null ? !createdId.equals(mfContent.createdId) : mfContent.createdId != null) return false;
        if (subTitle != null ? !subTitle.equals(mfContent.subTitle) : mfContent.subTitle != null) return false;
        if (title != null ? !title.equals(mfContent.title) : mfContent.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (subTitle != null ? subTitle.hashCode() : 0);
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (createdId != null ? createdId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
