package shop.company.service;

import java.io.Serializable;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.common.context.ApplicationContext;
import shop.common.util.DictUtils;
import shop.company.dao.QSLCourseDAO;
import shop.company.dao.QSLCourseTabDAO;
import shop.company.entity.QSLCourse;
import shop.company.entity.QSLCourseTab;

@Service("courseServiceImpl")
@Transactional
public class QSLCourseServiceImpl implements QSLCourseService {

	@Autowired()
	private QSLCourseDAO qSLCourseDAO;
	@Autowired()
	private QSLCourseTabDAO qSLCourseTabDAO;

	public QSLCourseDAO getQSLCourseDAO() {
		return qSLCourseDAO;
	}

	public void setQSLCourseDAO(QSLCourseDAO qSLCourseDAO) {
		this.qSLCourseDAO = qSLCourseDAO;
	}

	@Override
	public void deleteQSLCourse(QSLCourse o) {
		qSLCourseDAO.remove(o);
	}
	
	@Override
	public void deleteQSLCourseByIds(Integer[] ids){
		qSLCourseDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QSLCourse> findAllQSLCourse() {
		return qSLCourseDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLCourse> findAllQSLCourseWithPage(){
		return qSLCourseDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLCourse> findAllQSLCourseBySearchWithPage(QSLCourse o){
		return qSLCourseDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public QSLCourse getQSLCourseById(Serializable id) {
		return qSLCourseDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllQSLCourseByNameWithIdAndName(String name) {
		return qSLCourseDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveQSLCourse(QSLCourse o) {
		if(o != null && o.getCoach() != null && o.getCoach().getId() == null){
			o.setCoach(null);
		}
		qSLCourseDAO.persist(o);
	}

	@Override
	public void updateQSLCourse(QSLCourse o) {
		qSLCourseDAO.merge(o);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLCourse> findAllQSLCourseByIds(Integer[] cids) {
		return qSLCourseDAO.findAllByIds(cids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QSLCourse> findAllQSLCourseByTabId(Integer tabId) {
		return qSLCourseDAO.findAllByTabId(tabId);
	}
	
	@Override
	public QSLCourse getQSLCourseByVanuesTimesWeeks(Collection clist,String vanues, String times, String weeks) { 
		if(null == clist){
			return null;
		}
		
		for (Object obj : clist) {
			QSLCourse c = (QSLCourse)obj;
			if(c.getVanues().equals(vanues) && c.getTimezonedefine().equals(times) && weeks.equals(c.getWeekday()+"")){
				return c;
			}
		}
		
		return null;
	}
	
	@Override
	public List<QSLCourse> findAllQSLCourseByVanuesTimes(Collection clist,String vanues, String times) {
		if(null == clist){
			return null;
		}
		
		List<QSLCourse> list = new ArrayList<QSLCourse>();
		
		for (Object obj : clist) {
			QSLCourse c = (QSLCourse)obj;
			if(c.getVanues().equals(vanues) && c.getTimezonedefine().equals(times)){
				list.add(c);
			}
		}
		
		return list;
	}
	
	@Override
	public List<QSLCourse> findAllQSLCourseByVanues(Collection clist,String vanues) {
		if(null == clist){
			return null;
		}
		
		List<QSLCourse> list = new ArrayList<QSLCourse>();
		
		for (Object obj : clist) {
			QSLCourse c = (QSLCourse)obj;
			if(c.getVanues().equals(vanues)){
				list.add(c);
			}
		}
		
		return list;
	}
	
//	<c:set var="now" value="${mf:sysdate() }"/>
//	<c:set var="weeks" value="${mf:dictList('WEEK') }"/>
//	<c:set var="timeRanges" value="${mf:dictList('TIME_RANGE') }"/>
//	<c:set var="vanues" value="${mf:dictList('VANUES') }"/>
//	<c:set var="table" value="${mf:invokeMethod2('courseTabService','getQSLCourseTabByYearAndMonth',now.year,now.month) }"> </c:set>
//	<c:set var="courseList" value="${mf:invokeMethod1('courseService','findAllQSLCourseByTabId',table.id) }"> </c:set>

	@Override
	public Integer courseJsp() {
		
		Date now = new Date();
		QSLCourseTab table = null;
		
		HttpServletRequest req = ApplicationContext.getContext().getRequest();
		String id = req.getParameter("id");
		if(null != id){
			table = qSLCourseTabDAO.get(Integer.valueOf(id));
			now = new Date(table.getYear()-1900,table.getMonth()-1,1);
		}
		
		req.setAttribute("now", now);
		req.setAttribute("weeks", DictUtils.dict("WEEK"));
		req.setAttribute("timeRanges", DictUtils.dict("TIME_RANGE"));
		req.setAttribute("vanues", DictUtils.dict("VANUES"));
		
		if(null == table){
			table = qSLCourseTabDAO.findAllByYearAndMonth(now.getYear()+1900,now.getMonth()+1);
		}
		req.setAttribute("table", table);
		req.setAttribute("courseList", findAllQSLCourseByTabId(table.getId()));
		return null;
	}
}
