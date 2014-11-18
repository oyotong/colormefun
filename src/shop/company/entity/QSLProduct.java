package shop.company.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "qsl_product")
public class QSLProduct implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5491011256886422374L;
	// qsl_product CREATE TABLE `qsl_product` (
	// `ID` int(10) NOT NULL AUTO_INCREMENT,
	// `NAME` varchar(20) NOT NULL,
	// `DESCRIPTION` varchar(200) DEFAULT NULL,
	// `CONTENT` varchar(200) DEFAULT NULL,
	// `FAVOR` varchar(200) DEFAULT NULL,
	// `TOP_FLAG` char(1) DEFAULT NULL,
	// `SHOW_FLAG` char(1) DEFAULT NULL,
	// PRIMARY KEY (`ID`)
	// ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk

	private Integer id;
	private String name;
	private String description;
	private String content;
	private String favor;
	private String topFlag;
	private String showFlag;

	private String pic1;
	private String pic2;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFavor() {
		return favor;
	}

	public void setFavor(String favor) {
		this.favor = favor;
	}

	@Column(name = "top_flag")
	public String getTopFlag() {
		return topFlag;
	}

	public void setTopFlag(String topFlag) {
		this.topFlag = topFlag;
	}

	@Column(name = "show_flag")
	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((favor == null) ? 0 : favor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((showFlag == null) ? 0 : showFlag.hashCode());
		result = prime * result + ((topFlag == null) ? 0 : topFlag.hashCode());
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
		QSLProduct other = (QSLProduct) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (favor == null) {
			if (other.favor != null)
				return false;
		} else if (!favor.equals(other.favor))
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
		if (showFlag == null) {
			if (other.showFlag != null)
				return false;
		} else if (!showFlag.equals(other.showFlag))
			return false;
		if (topFlag == null) {
			if (other.topFlag != null)
				return false;
		} else if (!topFlag.equals(other.topFlag))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QSLProduct [id=" + id + ", name=" + name + ", description="
				+ description + ", content=" + content + ", favor=" + favor
				+ ", topFlag=" + topFlag + ", showFlag=" + showFlag + "]";
	}

}
