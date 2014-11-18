package shop.company.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="qsl_coach")
public class QSLCoach implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 693633544891437781L;
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String payRollname;//
	private String sex;
	private Integer height;
	private Integer height2;
	private Integer weight;
	private Integer weight2;
	private Date enrollDate;//
	private Date enrollDate2;//
	private Date termDate;//
	private Date termDate2;//
	private String description;
	private Integer companyNo;//
	
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;
	
//	private List<QSLCourse> courses;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="pay_rollname")
	public String getPayRollname() {
		return payRollname;
	}
	public void setPayRollname(String payRollname) {
		this.payRollname = payRollname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	@Column(name="Enroll_Date")
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	@Column(name="Term_Date")
	public Date getTermDate() {
		return termDate;
	}
	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="Company_No")
	public Integer getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(Integer companyNo) {
		this.companyNo = companyNo;
	}
//	@ManyToMany(fetch = FetchType.LAZY, targetEntity=QSLCourse.class)
//	@JoinTable(name = "QSLCoaXCourse", joinColumns = { @JoinColumn(name = "coach_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "course_Id", nullable = false, updatable = false) })	
//	public List<QSLCourse> getCourses() {
//		return courses;
//	}
//	public void setCourses(List<QSLCourse> courses) {
//		this.courses = courses;
//	}
	
	public String getPic1() {
		return pic1;
	}
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	public String getPic2() {
		return pic2;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	public String getPic3() {
		return pic3;
	}
	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}
	public String getPic4() {
		return pic4;
	}
	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}
	
	@Transient
	public Integer getHeight2() {
		return height2;
	}
	public void setHeight2(Integer height2) {
		this.height2 = height2;
	}
	@Transient
	public Integer getWeight2() {
		return weight2;
	}
	public void setWeight2(Integer weight2) {
		this.weight2 = weight2;
	}
	@Transient
	public Date getEnrollDate2() {
		return enrollDate2;
	}
	public void setEnrollDate2(Date enrollDate2) {
		this.enrollDate2 = enrollDate2;
	}
	@Transient
	public Date getTermDate2() {
		return termDate2;
	}
	public void setTermDate2(Date termDate2) {
		this.termDate2 = termDate2;
	}
	
	@Override
	public String toString() {
		return "QSLCoach [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", payRollname=" + payRollname
				+ ", sex=" + sex + ", height=" + height + ", weight=" + weight
				+ ", enrollDate=" + enrollDate + ", termDate=" + termDate
				+ ", description=" + description + ", companyNo=" + companyNo
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyNo == null) ? 0 : companyNo.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((enrollDate == null) ? 0 : enrollDate.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((payRollname == null) ? 0 : payRollname.hashCode());
		result = prime * result + ((pic1 == null) ? 0 : pic1.hashCode());
		result = prime * result + ((pic2 == null) ? 0 : pic2.hashCode());
		result = prime * result + ((pic3 == null) ? 0 : pic3.hashCode());
		result = prime * result + ((pic4 == null) ? 0 : pic4.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result
				+ ((termDate == null) ? 0 : termDate.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		QSLCoach other = (QSLCoach) obj;
		if (companyNo == null) {
			if (other.companyNo != null)
				return false;
		} else if (!companyNo.equals(other.companyNo))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (enrollDate == null) {
			if (other.enrollDate != null)
				return false;
		} else if (!enrollDate.equals(other.enrollDate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (payRollname == null) {
			if (other.payRollname != null)
				return false;
		} else if (!payRollname.equals(other.payRollname))
			return false;
		if (pic1 == null) {
			if (other.pic1 != null)
				return false;
		} else if (!pic1.equals(other.pic1))
			return false;
		if (pic2 == null) {
			if (other.pic2 != null)
				return false;
		} else if (!pic2.equals(other.pic2))
			return false;
		if (pic3 == null) {
			if (other.pic3 != null)
				return false;
		} else if (!pic3.equals(other.pic3))
			return false;
		if (pic4 == null) {
			if (other.pic4 != null)
				return false;
		} else if (!pic4.equals(other.pic4))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (termDate == null) {
			if (other.termDate != null)
				return false;
		} else if (!termDate.equals(other.termDate))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	
	
	
	
}
