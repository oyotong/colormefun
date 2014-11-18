package com.colormefun.entity;

import shop.common.util.DateUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tong on 2014/7/22.
 */
@Entity
@Table(name = "mf_case")
public class MfCase {
    private Integer caseNo;
    private String title;
    private Integer level;
    private Date startDate;
    private Date startDate2;
    private String startDateStr;
    private String timeRange;
    private String location;
    private String address;
    private BigDecimal ticketPrice;
    private String ticketPriceStr;
    private BigDecimal ticketPrice2;
    private String ticketPrice2Str;
    private Integer ticketNumber;
    private Integer ticketNumber2;
    private Integer remainingTicket;
    private Integer remainingTicket2;
    private Integer salesVolume;
    private Integer salesVolume2;
    private String picture;
    private String description;
    private String active;
    private String createdId;
    private Date createdDate;
    private Date createdDate2;

    private static final String rangeRegx = "^(\\d{1,2}):?(\\d{1,2})?([pm|am|PM|AM]*)\\s*-?\\s*(\\d{1,2}):?(\\d{1,2})?([pm|am|PM|AM]+)$";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "case_no")
    public Integer getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(Integer caseNo) {
        this.caseNo = caseNo;
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
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "time_range")
    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "ticket_price")
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Basic
    @Column(name = "ticket_number")
    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Basic
    @Column(name = "remaining_ticket")
    public Integer getRemainingTicket() {
        return remainingTicket;
    }

    public void setRemainingTicket(Integer remainingTicket) {
        this.remainingTicket = remainingTicket;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "created_id")
    public String getCreatedId() {
        return createdId;
    }

    @Basic
    @Column(name = "sales_volume")
    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public void setCreatedId(String createdId) {
        this.createdId = createdId;
    }

    @Transient
    public Date getStartDate2() {
        return startDate2;
    }

    public void setStartDate2(Date startDate2) {
        this.startDate2 = startDate2;
    }

    @Transient
    public Date getCreatedDate2() {
        return createdDate2;
    }

    public void setCreatedDate2(Date createdDate2) {
        this.createdDate2 = createdDate2;
    }

    @Transient
    public BigDecimal getTicketPrice2() {
        return ticketPrice2;
    }

    public void setTicketPrice2(BigDecimal ticketPrice2) {
        this.ticketPrice2 = ticketPrice2;
    }

    @Transient
    public Integer getTicketNumber2() {
        return ticketNumber2;
    }

    public void setTicketNumber2(Integer ticketNumber2) {
        this.ticketNumber2 = ticketNumber2;
    }

    @Transient
    public Integer getRemainingTicket2() {
        return remainingTicket2;
    }

    public void setRemainingTicket2(Integer remainingTicket2) {
        this.remainingTicket2 = remainingTicket2;
    }

    @Transient
    public Integer getSalesVolume2() {
        return salesVolume2;
    }

    public void setSalesVolume2(Integer salesVolume2) {
        this.salesVolume2 = salesVolume2;
    }
    @Transient
    public String getStartDateStr() {
        if(null == startDateStr){
            startDateStr = DateUtils.date(startDate, "yyyy年MM月dd日");
        }
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }
    @Transient
    public String getTicketPriceStr() {
        return ticketPriceStr;
    }

    public void setTicketPriceStr(String ticketPriceStr) {
        this.ticketPriceStr = ticketPriceStr;
    }
    @Transient
    public String getTicketPrice2Str() {
        return ticketPrice2Str;
    }

    public void setTicketPrice2Str(String ticketPrice2Str) {
        this.ticketPrice2Str = ticketPrice2Str;
    }

    @Transient
    public boolean isExpirated(){
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(this.startDate);
        try {
            String startHour = this.timeRange.replaceAll("^(\\d+):?(\\d+)?[pm|am]\\s(\\d+):?(\\d+)?[pm|am]$", "$1");
            String startMin = this.timeRange.replaceAll("^(\\d+):?(\\d+)?[pm|am]\\s(\\d+):?(\\d+)?[pm|am]$", "$2");
            startDate.set(Calendar.HOUR, new Integer(startHour));
            if(null != startMin || startMin.trim().length() > 0){
                startDate.set(Calendar.MINUTE, new Integer(startMin));
            }
        }catch (Exception e){

        }
        return (startDate.before(Calendar.getInstance()));
    }

//    public static void main(String[] args){
//        String range = "9am - 10:00pm";
//
//        String startHour = range.replaceAll(rangeRegx, "$1");
//        String startMin = range.replaceAll(rangeRegx, "$2");
//        System.out.println(startHour+"  "+ new Integer(startMin));
//
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfCase mfCase = (MfCase) o;

        if (caseNo != mfCase.caseNo) return false;
        if (active != null ? !active.equals(mfCase.active) : mfCase.active != null) return false;
        if (address != null ? !address.equals(mfCase.address) : mfCase.address != null) return false;
        if (createdDate != null ? !createdDate.equals(mfCase.createdDate) : mfCase.createdDate != null) return false;
        if (createdDate2 != null ? !createdDate2.equals(mfCase.createdDate2) : mfCase.createdDate2 != null)
            return false;
        if (createdId != null ? !createdId.equals(mfCase.createdId) : mfCase.createdId != null) return false;
        if (description != null ? !description.equals(mfCase.description) : mfCase.description != null) return false;
        if (level != null ? !level.equals(mfCase.level) : mfCase.level != null) return false;
        if (location != null ? !location.equals(mfCase.location) : mfCase.location != null) return false;
        if (picture != null ? !picture.equals(mfCase.picture) : mfCase.picture != null) return false;
        if (remainingTicket != null ? !remainingTicket.equals(mfCase.remainingTicket) : mfCase.remainingTicket != null)
            return false;
        if (remainingTicket2 != null ? !remainingTicket2.equals(mfCase.remainingTicket2) : mfCase.remainingTicket2 != null)
            return false;
        if (salesVolume != null ? !salesVolume.equals(mfCase.salesVolume) : mfCase.salesVolume != null) return false;
        if (salesVolume2 != null ? !salesVolume2.equals(mfCase.salesVolume2) : mfCase.salesVolume2 != null)
            return false;
        if (startDate != null ? !startDate.equals(mfCase.startDate) : mfCase.startDate != null) return false;
        if (startDate2 != null ? !startDate2.equals(mfCase.startDate2) : mfCase.startDate2 != null) return false;
        if (ticketNumber != null ? !ticketNumber.equals(mfCase.ticketNumber) : mfCase.ticketNumber != null)
            return false;
        if (ticketNumber2 != null ? !ticketNumber2.equals(mfCase.ticketNumber2) : mfCase.ticketNumber2 != null)
            return false;
        if (ticketPrice != null ? !ticketPrice.equals(mfCase.ticketPrice) : mfCase.ticketPrice != null) return false;
        if (ticketPrice2 != null ? !ticketPrice2.equals(mfCase.ticketPrice2) : mfCase.ticketPrice2 != null)
            return false;
        if (timeRange != null ? !timeRange.equals(mfCase.timeRange) : mfCase.timeRange != null) return false;
        if (title != null ? !title.equals(mfCase.title) : mfCase.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = caseNo;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (startDate2 != null ? startDate2.hashCode() : 0);
        result = 31 * result + (timeRange != null ? timeRange.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (ticketPrice != null ? ticketPrice.hashCode() : 0);
        result = 31 * result + (ticketPrice2 != null ? ticketPrice2.hashCode() : 0);
        result = 31 * result + (ticketNumber != null ? ticketNumber.hashCode() : 0);
        result = 31 * result + (ticketNumber2 != null ? ticketNumber2.hashCode() : 0);
        result = 31 * result + (remainingTicket != null ? remainingTicket.hashCode() : 0);
        result = 31 * result + (remainingTicket2 != null ? remainingTicket2.hashCode() : 0);
        result = 31 * result + (salesVolume != null ? salesVolume.hashCode() : 0);
        result = 31 * result + (salesVolume2 != null ? salesVolume2.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (createdId != null ? createdId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdDate2 != null ? createdDate2.hashCode() : 0);
        return result;
    }
}
