package shop.company.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.QSLCourseTabDAO;
import shop.company.entity.QSLCourseTab;

@Service("courseTabServiceImpl")
@Transactional
public class QSLCourseTabServiceImpl implements QSLCourseTabService {

	@Autowired()
	private QSLCourseTabDAO qSLCourseTabDAO;

	public QSLCourseTabDAO getQSLCourseTabDAO() {
		return qSLCourseTabDAO;
	}

	public void setQSLCourseTabDAO(QSLCourseTabDAO qSLCourseTabDAO) {
		this.qSLCourseTabDAO = qSLCourseTabDAO;
	}

	@Override
	public void deleteQSLCourseTab(QSLCourseTab o) {
		qSLCourseTabDAO.remove(o);
	}
	
	@Override
	public void deleteQSLCourseTabByIds(Integer[] ids){
		qSLCourseTabDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QSLCourseTab> findAllQSLCourseTab() {
		return qSLCourseTabDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLCourseTab> findAllQSLCourseTabWithPage(){
		return qSLCourseTabDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLCourseTab> findAllQSLCourseTabBySearchWithPage(QSLCourseTab o){
		return qSLCourseTabDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public QSLCourseTab getQSLCourseTabById(Serializable id) {
		return qSLCourseTabDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllQSLCourseTabByNameWithIdAndName(String name) {
		return qSLCourseTabDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveQSLCourseTab(QSLCourseTab o) {
		qSLCourseTabDAO.persist(o);
	}

	@Override
	public void updateQSLCourseTab(QSLCourseTab o) {
		qSLCourseTabDAO.merge(o);
	}
	
	@Override
	public List<QSLCourseTab> findAllQSLCourseTabByIds(Integer[] cids) {
		return qSLCourseTabDAO.findAllByIds(cids);
	}

	@Override
	public QSLCourseTab getQSLCourseTabByYearAndMonth(Integer year,
			Integer month) {
		return qSLCourseTabDAO.findAllByYearAndMonth(year,month);
	}

}
