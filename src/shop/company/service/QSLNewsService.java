package shop.company.service;

import java.io.Serializable;
import java.util.*;

import shop.company.entity.QSLNews;

public interface QSLNewsService {
	
	public void saveQSLNews(QSLNews o);
	
	public void updateQSLNews(QSLNews o);
	
	public void deleteQSLNews(QSLNews o);
	
	public void deleteQSLNewsByIds(Integer[] ids);
	
	public QSLNews getQSLNewsById(Serializable id);
	
	public List<QSLNews> findAllQSLNews();
	
	public List<QSLNews> findAllQSLNewsWithPage();
	
	public List<QSLNews> findAllQSLNewsBySearchWithPage(QSLNews o);
	
	public Map findAllQSLNewsByNameWithIdAndName(String name);
	
	public List<QSLNews> findAllQSLNewsByIds(Integer[] cids);

	List<QSLNews> findTopNNews(String type, Integer n);

	public QSLNews getContact();

	public QSLNews getJoin();
	
}
