package shop.company.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/*
 qsl_course_tab  CREATE TABLE `qsl_course_tab` (                       
 `ID` Integer(10) NOT NULL AUTO_INCREMENT,               
 `TITLE` varchar(100) NOT NULL,                      
 `YEAR` Integer(10) DEFAULT NULL,                        
 `MONTH` Integer(10) DEFAULT NULL,                       
 `DESCRIPTION` varchar(2000) DEFAULT NULL,           
 `ENTRY_ID` Integer(10) DEFAULT NULL,                    
 `ENTRY_DATETIME` datetime DEFAULT NULL,             
 `DELETE_ID` Integer(10) DEFAULT NULL,                   
 `DELETE_DATETIME` datetime DEFAULT NULL,            
 `COMPANY_NO` Integer(1) DEFAULT NULL,                   
 PRIMARY KEY (`ID`)                                  
 ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk  
 */
@Entity
@Table(name = "qsl_course_tab")
public class QSLCourseTab implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2291931548139396262L;

	private Integer id;
	private String title;
	private Integer year;
	private Integer month;
	private String description;
	private Integer entryId;
	private Date entryDatetime;
	private Integer deleteId;
	private Date deleteDatetime;
	private Integer companyNo;

	private Set<QSLCourse> courses;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "entry_Id")
	public Integer getEntryId() {
		return entryId;
	}

	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}

	@Column(name = "entry_Datetime")
	public Date getEntryDatetime() {
		return entryDatetime;
	}

	public void setEntryDatetime(Date entryDatetime) {
		this.entryDatetime = entryDatetime;
	}

	@Column(name = "delete_Id")
	public Integer getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(Integer deleteId) {
		this.deleteId = deleteId;
	}

	@Column(name = "delete_Datetime")
	public Date getDeleteDatetime() {
		return deleteDatetime;
	}

	public void setDeleteDatetime(Date deleteDatetime) {
		this.deleteDatetime = deleteDatetime;
	}

	@Column(name = "company_no")
	public Integer getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(Integer companyNo) {
		this.companyNo = companyNo;
	}

	@OneToMany(mappedBy = "table", targetEntity = QSLCourse.class)
	@OrderBy("vanues, timezonedefine, weekday")
	public Set<QSLCourse> getCourses() {
		return courses;
	}

	public void setCourses(Set<QSLCourse> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "QSLCourseTab [id=" + id + ", title=" + title + ", description="
				+ description + ", entryDatetime=" + entryDatetime
				+ ", deleteDatetime=" + deleteDatetime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deleteDatetime == null) ? 0 : deleteDatetime.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((entryDatetime == null) ? 0 : entryDatetime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QSLCourseTab other = (QSLCourseTab) obj;
		if (deleteDatetime == null) {
			if (other.deleteDatetime != null)
				return false;
		} else if (!deleteDatetime.equals(other.deleteDatetime))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (entryDatetime == null) {
			if (other.entryDatetime != null)
				return false;
		} else if (!entryDatetime.equals(other.entryDatetime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
