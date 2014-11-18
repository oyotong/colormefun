package shop.company.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="qsl_photo")
public class QSLPhoto implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -7516682983778702388L;

//	CREATE TABLE `qsl_photo` (                                                                                                                
//            `CATEGORY` varchar(10) NOT NULL COMMENT '类别，如健身教练，场地，代表不同类别的图片，对应不同的categoryID',  
//            `CATEGORY_ID` int(10) NOT NULL,                                                                                                         
//            `SIZE_TYPE` char(1) NOT NULL COMMENT '大图片，中图片，小图片',                                                               
//            `LINE_NO` int(3) NOT NULL COMMENT '序列号',                                                                                          
//            `PIC_PATH` varchar(50) NOT NULL default '' COMMENT '照片存放的路径',                                                             
//            PRIMARY KEY  (`CATEGORY`,`CATEGORY_ID`,`SIZE_TYPE`,`LINE_NO`)                                                                           
//          ) ENGINE=InnoDB DEFAULT CHARSET=gbk          
	
	private Integer id;
	
	private String category;
	private Integer categoryId;
	private String sizeType;
	private Integer lineNo;
	private String picPath;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name="category_Id")
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	@Column(name="size_Type")
	public String getSizeType() {
		return sizeType;
	}
	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}
	@Column(name="line_No")
	public Integer getLineNo() {
		return lineNo;
	}
	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}
	@Column(name="pic_Path")
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lineNo == null) ? 0 : lineNo.hashCode());
		result = prime * result + ((picPath == null) ? 0 : picPath.hashCode());
		result = prime * result
				+ ((sizeType == null) ? 0 : sizeType.hashCode());
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
		QSLPhoto other = (QSLPhoto) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lineNo == null) {
			if (other.lineNo != null)
				return false;
		} else if (!lineNo.equals(other.lineNo))
			return false;
		if (picPath == null) {
			if (other.picPath != null)
				return false;
		} else if (!picPath.equals(other.picPath))
			return false;
		if (sizeType == null) {
			if (other.sizeType != null)
				return false;
		} else if (!sizeType.equals(other.sizeType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "QSLPhoto [id=" + id + ", category=" + category
				+ ", categoryId=" + categoryId + ", sizeType=" + sizeType
				+ ", lineNo=" + lineNo + ", picPath=" + picPath + "]";
	}
	
	
	
}
