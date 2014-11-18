package shop.company.service;

import java.io.Serializable;
import java.util.*;

import shop.company.entity.QSLPhoto;

public interface QSLPhotoService {
	
	public void saveQSLPhoto(QSLPhoto o);
	
	public void updateQSLPhoto(QSLPhoto o);
	
	public void deleteQSLPhoto(QSLPhoto o);
	
	public void deleteQSLPhotoByIds(Integer[] ids);
	
	public QSLPhoto getQSLPhotoById(Serializable id);
	
	public List<QSLPhoto> findAllQSLPhoto();
	
	public List<QSLPhoto> findAllQSLPhotoWithPage();
	
	public List<QSLPhoto> findAllQSLPhotoBySearchWithPage(QSLPhoto o);
	
	public Map findAllQSLPhotoByNameWithIdAndName(String name);
	
	public List<QSLPhoto> findAllQSLPhotoByIds(Integer[] cids);
	
}
