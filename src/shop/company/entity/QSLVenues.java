package shop.company.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "qsl_vanues")
public class QSLVenues implements Serializable {

	/*
	 * CREATE TABLE `qsl_vanues` ( `ID` int(11) NOT NULL auto_increment, `NAME`
	 * varchar(20) NOT NULL, `DESCRIPTION` varchar(2000) default NULL,
	 * `SHOW_FLAG` char(1) default NULL, `COMPANY_NO` int(11) default NULL,
	 * `ICON` varchar(50) NOT NULL, `IMAGE` varchar(50) NOT NULL, PRIMARY KEY
	 * (`ID`), UNIQUE KEY `ID` (`ID`) ) ENGINE=InnoDB DEFAULT CHARSET=gbk
	 * MAX_ROWS=9999;
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 693633544891437781L;

	private Integer id;
	private String name;
	private String showFlag;
	private String description;
	private Integer companyNo;//
	private String icon;
	private String image;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="show_Flag")
	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="company_No")
	public Integer getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(Integer companyNo) {
		this.companyNo = companyNo;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyNo == null) ? 0 : companyNo.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((showFlag == null) ? 0 : showFlag.hashCode());
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
		QSLVenues other = (QSLVenues) obj;
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
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (showFlag == null) {
			if (other.showFlag != null)
				return false;
		} else if (!showFlag.equals(other.showFlag))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QSLVenues [id=" + id + ", name=" + name + ", showFlag="
				+ showFlag + ", description=" + description + ", companyNo="
				+ companyNo + ", icon=" + icon + ", image=" + image + "]";
	}

}
