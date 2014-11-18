package shop.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="qsl_course")
public class QSLCourse {
	
	private Integer id;
	private Integer weekday;
	private String name;
	private String description;
	private String vanues;
	private String timezonedefine;
	private String pic1;
	
	private QSLCourseTab table;
	private QSLCoach coach;
	private String coachName;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWeekday() {
		return weekday;
	}
	public void setWeekday(Integer weekday) {
		this.weekday = weekday;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVanues() {
		return vanues;
	}
	public void setVanues(String vanues) {
		this.vanues = vanues;
	}
	
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=QSLCoach.class)
	public QSLCoach getCoach() {
		return coach;
	}
	public void setCoach(QSLCoach coachs) {
		this.coach = coachs;
	}
	public String getTimezonedefine() {
		return timezonedefine;
	}
	public void setTimezonedefine(String timezonedefine) {
		this.timezonedefine = timezonedefine;
	}
	
	public String getPic1() {
		return pic1;
	}
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
		
	@Column(name="coach_name")
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	
	@ManyToOne(targetEntity=QSLCourseTab.class,fetch=FetchType.EAGER)
    @JoinColumn(name="TAB_ID", nullable=false)
	public QSLCourseTab getTable() {
		return table;
	}
	public void setTable(QSLCourseTab table) {
		this.table = table;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coach == null) ? 0 : coach.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((timezonedefine == null) ? 0 : timezonedefine.hashCode());
		result = prime * result + ((weekday == null) ? 0 : weekday.hashCode());
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
		QSLCourse other = (QSLCourse) obj;
		if (coach == null) {
			if (other.coach != null)
				return false;
		} else if (!coach.equals(other.coach))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (timezonedefine == null) {
			if (other.timezonedefine != null)
				return false;
		} else if (!timezonedefine.equals(other.timezonedefine))
			return false;
		if (weekday == null) {
			if (other.weekday != null)
				return false;
		} else if (!weekday.equals(other.weekday))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "QSLCourse [id=" + id + ", weekday=" + weekday + ", name="
				+ name + ", description=" + description + ", vanues=" + vanues
				+ ", coachs=" + coach + ", timezonedefine=" + timezonedefine
				+ "]";
	}
	
	
}
