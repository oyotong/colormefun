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
@Table(name = "qsl_news")
public class QSLNews implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -994723022067268297L;

	private Integer id;
	private String title;
	private String content;
	private String entryId;
	private Date entryDatetime;
	private Date entryDatetime2;
	private String topFlag;
	private String showFlag;
	private String categoryType;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "entry_id")
	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	@Column(name = "entry_datetime")
	public Date getEntryDatetime() {
		return entryDatetime;
	}

	public void setEntryDatetime(Date entryDatetime) {
		this.entryDatetime = entryDatetime;
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
	
	@Column(name="CATEGORY_TYPE")
	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	@Transient
	public Date getEntryDatetime2() {
		return entryDatetime2;
	}

	public void setEntryDatetime2(Date entryDatetime2) {
		this.entryDatetime2 = entryDatetime2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((entryDatetime == null) ? 0 : entryDatetime.hashCode());
		result = prime * result + ((entryId == null) ? 0 : entryId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((showFlag == null) ? 0 : showFlag.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		QSLNews other = (QSLNews) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (entryDatetime == null) {
			if (other.entryDatetime != null)
				return false;
		} else if (!entryDatetime.equals(other.entryDatetime))
			return false;
		if (entryId == null) {
			if (other.entryId != null)
				return false;
		} else if (!entryId.equals(other.entryId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (showFlag == null) {
			if (other.showFlag != null)
				return false;
		} else if (!showFlag.equals(other.showFlag))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
		return "QSLNews [id=" + id + ", title=" + title + ", content="
				+ content + ", entryId=" + entryId + ", entryDatetime="
				+ entryDatetime + ", topFlag=" + topFlag + ", showFlag="
				+ showFlag + "]";
	}

}
