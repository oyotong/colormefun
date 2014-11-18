package com.colormefun.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tong on 2014/7/22.
 */
@Entity
@Table(name = "mf_user")
public class MfUser {
    private Integer userNo;
    private String userName;
    private String mobilePhone;
    private String password;
    private String password2;
    private String email;
    private Date birthday;
    private Date birthday2;
    private String sex;
    private String favorite;
    private String myColor;
    private String pic1;
    private String letMeKnow;
    private String active="Y";
    private Date registeredDate;
    private Date registeredDate2;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_no")
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "mobile_phone", unique = true)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "let_me_know")
    public String getLetMeKnow() {
        return letMeKnow;
    }

    public void setLetMeKnow(String letMeKnow) {
        this.letMeKnow = letMeKnow;
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
    @Column(name = "registered_date")
    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "favorite")
    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    @Basic
    @Column(name = "my_color")
    public String getMyColor() {
        return myColor;
    }

    public void setMyColor(String myColor) {
        this.myColor = myColor;
    }

    @Basic
    @Column(name = "pic1")
    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    @Transient
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Transient
    public Date getRegisteredDate2() {
        return registeredDate2;
    }

    public void setRegisteredDate2(Date registeredDate2) {
        this.registeredDate2 = registeredDate2;
    }

    @Transient
    public Date getBirthday2() {
        return birthday2;
    }

    public void setBirthday2(Date birthday2) {
        this.birthday2 = birthday2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfUser mfUser = (MfUser) o;

        if (active != null ? !active.equals(mfUser.active) : mfUser.active != null) return false;
        if (birthday != null ? !birthday.equals(mfUser.birthday) : mfUser.birthday != null) return false;
        if (email != null ? !email.equals(mfUser.email) : mfUser.email != null) return false;
        if (favorite != null ? !favorite.equals(mfUser.favorite) : mfUser.favorite != null) return false;
        if (letMeKnow != null ? !letMeKnow.equals(mfUser.letMeKnow) : mfUser.letMeKnow != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(mfUser.mobilePhone) : mfUser.mobilePhone != null) return false;
        if (myColor != null ? !myColor.equals(mfUser.myColor) : mfUser.myColor != null) return false;
        if (password != null ? !password.equals(mfUser.password) : mfUser.password != null) return false;
        if (pic1 != null ? !pic1.equals(mfUser.pic1) : mfUser.pic1 != null) return false;
        if (registeredDate != null ? !registeredDate.equals(mfUser.registeredDate) : mfUser.registeredDate != null)
            return false;
        if (sex != null ? !sex.equals(mfUser.sex) : mfUser.sex != null) return false;
        if (userName != null ? !userName.equals(mfUser.userName) : mfUser.userName != null) return false;
        if (userNo != null ? !userNo.equals(mfUser.userNo) : mfUser.userNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userNo != null ? userNo.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (favorite != null ? favorite.hashCode() : 0);
        result = 31 * result + (myColor != null ? myColor.hashCode() : 0);
        result = 31 * result + (pic1 != null ? pic1.hashCode() : 0);
        result = 31 * result + (letMeKnow != null ? letMeKnow.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (registeredDate != null ? registeredDate.hashCode() : 0);
        return result;
    }
}
