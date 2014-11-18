package shop.company.service;

import java.io.Serializable;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import shop.company.entity.QSLCourse;
import shop.company.entity.QSLCourseTab;

public interface QSLCourseService {
	
	public void saveQSLCourse(QSLCourse o);
	
	public void updateQSLCourse(QSLCourse o);
	
	public void deleteQSLCourse(QSLCourse o);
	
	public void deleteQSLCourseByIds(Integer[] ids);
	
	public QSLCourse getQSLCourseById(Serializable id);
	
	public List<QSLCourse> findAllQSLCourse();
	
	public List<QSLCourse> findAllQSLCourseWithPage();
	
	public List<QSLCourse> findAllQSLCourseBySearchWithPage(QSLCourse o);
	
	public Map findAllQSLCourseByNameWithIdAndName(String name);
	
	public List<QSLCourse> findAllQSLCourseByIds(Integer[] cids);
	
	List<QSLCourse> findAllQSLCourseByTabId(Integer tabId);

	QSLCourse getQSLCourseByVanuesTimesWeeks(Collection clist, String vanues,
                                             String times, String weeks);

	List<QSLCourse> findAllQSLCourseByVanuesTimes(Collection clist,
                                                  String vanues, String times);
	
	List<QSLCourse> findAllQSLCourseByVanues(Collection clist, String vanues);

	Integer courseJsp();
	
}
