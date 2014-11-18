package shop.company.service;

import java.io.Serializable;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.QSLCoachDAO;
import shop.company.entity.QSLCoach;

@Service("coachServiceImpl")
@Transactional
public class QSLCoachServiceImpl implements QSLCoachService {

	@Autowired()
	private QSLCoachDAO qSLCoachDAO;

	public QSLCoachDAO getQSLCoachDAO() {
		return qSLCoachDAO;
	}

	public void setQSLCoachDAO(QSLCoachDAO qSLCoachDAO) {
		this.qSLCoachDAO = qSLCoachDAO;
	}

	@Override
	public void deleteQSLCoach(QSLCoach o) {
		qSLCoachDAO.remove(o);
	}
	
	@Override
	public void deleteQSLCoachByIds(Integer[] ids){
		qSLCoachDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QSLCoach> findAllQSLCoach() {
		return qSLCoachDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLCoach> findAllQSLCoachWithPage(){
		return qSLCoachDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLCoach> findAllQSLCoachBySearchWithPage(QSLCoach o){
		return qSLCoachDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public QSLCoach getQSLCoachById(Serializable id) {
		return qSLCoachDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllQSLCoachByNameWithIdAndName(String name) {
		return qSLCoachDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveQSLCoach(QSLCoach o) {
		qSLCoachDAO.persist(o);
	}

	@Override
	public void updateQSLCoach(QSLCoach o) {
		qSLCoachDAO.merge(o);
	}
	
	@Override
	public List<QSLCoach> findAllQSLCoachByIds(Integer[] cids) {
		return qSLCoachDAO.findAllByIds(cids);
	}

	@Override
	public List<QSLCoach> findTopNCoach(Integer n) {
		
		return qSLCoachDAO.findTopN(n);
	}

}
