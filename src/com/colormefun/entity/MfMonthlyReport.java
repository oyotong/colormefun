package com.colormefun.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by tong-note on 2014/8/21.
 */
@Entity
@Table(name = "mf_monthly_report")
@IdClass(MfMonthlyReportPK.class)
public class MfMonthlyReport {
    private String year;
    private String month;
    private int caseTotal = 0;
    private int orderTotal = 0;
    private Integer ticketTotal = 0;
    private BigDecimal priceTotal = new BigDecimal("0");
    private BigDecimal couponTotal = new BigDecimal("0");
    private int realCaseTotal = 0;
    private int realOrderTotal = 0;
    private Integer realTicketTotal = 0;
    private BigDecimal realPriceTotal = new BigDecimal("0");
    private BigDecimal realCouponTotal = new BigDecimal("0");
    private String status;
    private String comment;
    private String createdId = "-1";
    private Timestamp createdDate = new Timestamp(new Date().getTime());

    @Id
    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Id
    @Column(name = "month")
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Basic
    @Column(name = "case_total")
    public int getCaseTotal() {
        return caseTotal;
    }

    public void setCaseTotal(int caseTotal) {
        this.caseTotal = caseTotal;
    }

    @Basic
    @Column(name = "order_total")
    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Basic
    @Column(name = "ticket_total")
    public Integer getTicketTotal() {
        return ticketTotal;
    }

    public void setTicketTotal(Integer ticketTotal) {
        this.ticketTotal = ticketTotal;
    }

    @Basic
    @Column(name = "price_total")
    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }

    @Basic
    @Column(name = "coupon_total")
    public BigDecimal getCouponTotal() {
        return couponTotal;
    }

    public void setCouponTotal(BigDecimal couponTotal) {
        this.couponTotal = couponTotal;
    }

    @Basic
    @Column(name = "real_case_total")
    public int getRealCaseTotal() {
        return realCaseTotal;
    }

    public void setRealCaseTotal(int realCaseTotal) {
        this.realCaseTotal = realCaseTotal;
    }

    @Basic
    @Column(name = "real_order_total")
    public int getRealOrderTotal() {
        return realOrderTotal;
    }

    public void setRealOrderTotal(int realOrderTotal) {
        this.realOrderTotal = realOrderTotal;
    }

    @Basic
    @Column(name = "real_ticket_total")
    public Integer getRealTicketTotal() {
        return realTicketTotal;
    }

    public void setRealTicketTotal(Integer realTicketTotal) {
        this.realTicketTotal = realTicketTotal;
    }

    @Basic
    @Column(name = "real_price_total")
    public BigDecimal getRealPriceTotal() {
        return realPriceTotal;
    }

    public void setRealPriceTotal(BigDecimal realPriceTotal) {
        this.realPriceTotal = realPriceTotal;
    }

    @Basic
    @Column(name = "real_coupon_total")
    public BigDecimal getRealCouponTotal() {
        return realCouponTotal;
    }

    public void setRealCouponTotal(BigDecimal realCouponTotal) {
        this.realCouponTotal = realCouponTotal;
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
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

        MfMonthlyReport that = (MfMonthlyReport) o;

        if (caseTotal != that.caseTotal) return false;
        if (month != null ? !month.equals(that.month) : that.month != null) return false;
        if (orderTotal != that.orderTotal) return false;
        if (realCaseTotal != that.realCaseTotal) return false;
        if (realOrderTotal != that.realOrderTotal) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (couponTotal != null ? !couponTotal.equals(that.couponTotal) : that.couponTotal != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdId != null ? !createdId.equals(that.createdId) : that.createdId != null) return false;
        if (priceTotal != null ? !priceTotal.equals(that.priceTotal) : that.priceTotal != null) return false;
        if (realCouponTotal != null ? !realCouponTotal.equals(that.realCouponTotal) : that.realCouponTotal != null)
            return false;
        if (realPriceTotal != null ? !realPriceTotal.equals(that.realPriceTotal) : that.realPriceTotal != null)
            return false;
        if (realTicketTotal != null ? !realTicketTotal.equals(that.realTicketTotal) : that.realTicketTotal != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (ticketTotal != null ? !ticketTotal.equals(that.ticketTotal) : that.ticketTotal != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = year != null ? year.hashCode() : 0;
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + caseTotal;
        result = 31 * result + orderTotal;
        result = 31 * result + (ticketTotal != null ? ticketTotal.hashCode() : 0);
        result = 31 * result + (priceTotal != null ? priceTotal.hashCode() : 0);
        result = 31 * result + (couponTotal != null ? couponTotal.hashCode() : 0);
        result = 31 * result + realCaseTotal;
        result = 31 * result + realOrderTotal;
        result = 31 * result + (realTicketTotal != null ? realTicketTotal.hashCode() : 0);
        result = 31 * result + (realPriceTotal != null ? realPriceTotal.hashCode() : 0);
        result = 31 * result + (realCouponTotal != null ? realCouponTotal.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (createdId != null ? createdId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
