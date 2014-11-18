package com.colormefun.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by tong-note on 2014/8/21.
 */
public class MfMonthlyReportPK implements Serializable {
    private String year;
    private int month;

    @Column(name = "year")
    @Id
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Column(name = "month")
    @Id
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MfMonthlyReportPK that = (MfMonthlyReportPK) o;

        if (month != that.month) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = year != null ? year.hashCode() : 0;
        result = 31 * result + month;
        return result;
    }
}
