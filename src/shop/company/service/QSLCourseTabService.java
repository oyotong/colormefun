package shop.company.service;

import java.io.Serializable;
import java.util.*;

import shop.company.entity.QSLCourseTab;

public interface QSLCourseTabService {
	
	public void saveQSLCourseTab(QSLCourseTab o);
	
	public void updateQSLCourseTab(QSLCourseTab o);
	
	public void deleteQSLCourseTab(QSLCourseTab o);
	
	public void deleteQSLCourseTabByIds(Integer[] ids);
	
	public QSLCourseTab getQSLCourseTabById(Serializable id);
	
	public List<QSLCourseTab> findAllQSLCourseTab();
	
	public List<QSLCourseTab> findAllQSLCourseTabWithPage();
	
	public List<QSLCourseTab> findAllQSLCourseTabBySearchWithPage(QSLCourseTab o);
	
	public Map findAllQSLCourseTabByNameWithIdAndName(String name);
	
	public List<QSLCourseTab> findAllQSLCourseTabByIds(Integer[] cids);
	
	public QSLCourseTab getQSLCourseTabByYearAndMonth(Integer year, Integer month);
	
}
