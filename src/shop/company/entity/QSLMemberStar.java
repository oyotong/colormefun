package shop.company.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="qsl_member_star")
public class QSLMemberStar implements Serializable{
//	`ID` int(11) NOT NULL auto_increment,  
//    `YEAR` int(11) NOT NULL,               
//    `MONTH` int(11) NOT NULL,              
//    `EMPLOYER` varchar(10) NOT NULL,       
//    `SEX` char(1) default NULL,            
//    `WEIGHT` float NOT NULL,               
//    `WAISTLINE` float NOT NULL,            
//    `FAT` float NOT NULL,                  
//    `SCORE` float NOT NULL,                
//    `WEIGHT_AFTER` float NOT NULL,         
//    `WAISTLINE_AFTER` float NOT NULL,      
//    `FAT_AFTER` float NOT NULL,            
//    `SCORE_AFTER` float NOT NULL,          
//    `TITLE` varchar(10) NOT NULL,          
//    `CONTENT` varchar(2000) NOT NULL,      
//    `SHOWFLAG` char(1) default NULL,  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5272582749647497210L;
	private Integer id;
	private Integer year;
	private Integer month;
	private String employer;
	private String sex;
	private Double weight;
	private Double waistline;
	private Double fat;
	private Double score;
	private Double weightAfter;
	private Double waistlineAfter;
	private Double fatAfter;
	private Double scoreAfter;
	private String title;
	private String content;
	private String showflag;
	
	private String pic;
	private String picAfter;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getWaistline() {
		return waistline;
	}
	public void setWaistline(Double waistline) {
		this.waistline = waistline;
	}
	public Double getFat() {
		return fat;
	}
	public void setFat(Double fat) {
		this.fat = fat;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	@Column(name="weight_after")
	public Double getWeightAfter() {
		return weightAfter;
	}
	public void setWeightAfter(Double weightAfter) {
		this.weightAfter = weightAfter;
	}
	@Column(name="Waistline_After")
	public Double getWaistlineAfter() {
		return waistlineAfter;
	}
	public void setWaistlineAfter(Double waistlineAfter) {
		this.waistlineAfter = waistlineAfter;
	}
	@Column(name="fat_After")
	public Double getFatAfter() {
		return fatAfter;
	}
	public void setFatAfter(Double fatAfter) {
		this.fatAfter = fatAfter;
	}
	@Column(name="score_After")
	public Double getScoreAfter() {
		return scoreAfter;
	}
	public void setScoreAfter(Double scoreAfter) {
		this.scoreAfter = scoreAfter;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getShowflag() {
		return showflag;
	}
	public void setShowflag(String showflag) {
		this.showflag = showflag;
	}
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	@Column(name="pic_after")
	public String getPicAfter() {
		return picAfter;
	}
	public void setPicAfter(String picAfter) {
		this.picAfter = picAfter;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((employer == null) ? 0 : employer.hashCode());
		result = prime * result + ((fat == null) ? 0 : fat.hashCode());
		result = prime * result
				+ ((fatAfter == null) ? 0 : fatAfter.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result
				+ ((scoreAfter == null) ? 0 : scoreAfter.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result
				+ ((showflag == null) ? 0 : showflag.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result
				+ ((waistline == null) ? 0 : waistline.hashCode());
		result = prime * result
				+ ((waistlineAfter == null) ? 0 : waistlineAfter.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		result = prime * result
				+ ((weightAfter == null) ? 0 : weightAfter.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		QSLMemberStar other = (QSLMemberStar) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (employer == null) {
			if (other.employer != null)
				return false;
		} else if (!employer.equals(other.employer))
			return false;
		if (fat == null) {
			if (other.fat != null)
				return false;
		} else if (!fat.equals(other.fat))
			return false;
		if (fatAfter == null) {
			if (other.fatAfter != null)
				return false;
		} else if (!fatAfter.equals(other.fatAfter))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (scoreAfter == null) {
			if (other.scoreAfter != null)
				return false;
		} else if (!scoreAfter.equals(other.scoreAfter))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (showflag == null) {
			if (other.showflag != null)
				return false;
		} else if (!showflag.equals(other.showflag))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (waistline == null) {
			if (other.waistline != null)
				return false;
		} else if (!waistline.equals(other.waistline))
			return false;
		if (waistlineAfter == null) {
			if (other.waistlineAfter != null)
				return false;
		} else if (!waistlineAfter.equals(other.waistlineAfter))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		if (weightAfter == null) {
			if (other.weightAfter != null)
				return false;
		} else if (!weightAfter.equals(other.weightAfter))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "QSLMemberStar [id=" + id + ", year=" + year + ", month="
				+ month + ", employer=" + employer + ", sex=" + sex
				+ ", weight=" + weight + ", waistline=" + waistline + ", fat="
				+ fat + ", score=" + score + ", weightAfter=" + weightAfter
				+ ", waistlineAfter=" + waistlineAfter + ", fatAfter="
				+ fatAfter + ", scoreAfter=" + scoreAfter + ", title=" + title
				+ ", content=" + content + ", showflag=" + showflag + "]";
	}
	
	
	
}
