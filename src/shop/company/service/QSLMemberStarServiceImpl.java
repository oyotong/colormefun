package shop.company.service;

import java.io.Serializable;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.QSLMemberStarDAO;
import shop.company.entity.QSLMemberStar;

@Service("starServiceImpl")
@Transactional
public class QSLMemberStarServiceImpl implements QSLMemberStarService {

	@Autowired()
	private QSLMemberStarDAO qSLMemberStarDAO;

	public QSLMemberStarDAO getQSLMemberStarDAO() {
		return qSLMemberStarDAO;
	}

	public void setQSLMemberStarDAO(QSLMemberStarDAO qSLMemberStarDAO) {
		this.qSLMemberStarDAO = qSLMemberStarDAO;
	}

	@Override
	public void deleteQSLMemberStar(QSLMemberStar o) {
		qSLMemberStarDAO.remove(o);
	}
	
	@Override
	public void deleteQSLMemberStarByIds(Integer[] ids){
		qSLMemberStarDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QSLMemberStar> findAllQSLMemberStar() {
		return qSLMemberStarDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLMemberStar> findAllQSLMemberStarWithPage(){
		return qSLMemberStarDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLMemberStar> findAllQSLMemberStarBySearchWithPage(QSLMemberStar o){
		return qSLMemberStarDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public QSLMemberStar getQSLMemberStarById(Serializable id) {
		return qSLMemberStarDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllQSLMemberStarByNameWithIdAndName(String name) {
		return qSLMemberStarDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveQSLMemberStar(QSLMemberStar o) {
		qSLMemberStarDAO.persist(o);
	}

	@Override
	public void updateQSLMemberStar(QSLMemberStar o) {
		qSLMemberStarDAO.merge(o);
	}
	
	@Override
	public List<QSLMemberStar> findAllQSLMemberStarByIds(Integer[] cids) {
		return qSLMemberStarDAO.findAllByIds(cids);
	}

	@Override
	public List<QSLMemberStar> findTopNStar(Integer n) {
		
		return qSLMemberStarDAO.findTopNStar(n);
	}

}
