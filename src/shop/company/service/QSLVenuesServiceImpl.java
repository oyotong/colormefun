package shop.company.service;

import java.io.Serializable;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.QSLVenuesDAO;
import shop.company.entity.QSLVenues;

@Service("venuesServiceImpl")
@Transactional
public class QSLVenuesServiceImpl implements QSLVenuesService {

	@Autowired()
	private QSLVenuesDAO qSLVenuesDAO;

	public QSLVenuesDAO getQSLVenuesDAO() {
		return qSLVenuesDAO;
	}

	public void setQSLVenuesDAO(QSLVenuesDAO qSLVenuesDAO) {
		this.qSLVenuesDAO = qSLVenuesDAO;
	}

	@Override
	public void deleteQSLVenues(QSLVenues o) {
		qSLVenuesDAO.remove(o);
	}
	
	@Override
	public void deleteQSLVenuesByIds(Integer[] ids){
		qSLVenuesDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QSLVenues> findAllQSLVenues() {
		return qSLVenuesDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLVenues> findAllQSLVenuesWithPage(){
		return qSLVenuesDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLVenues> findAllQSLVenuesBySearchWithPage(QSLVenues o){
		return qSLVenuesDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public QSLVenues getQSLVenuesById(Serializable id) {
		return qSLVenuesDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllQSLVenuesByNameWithIdAndName(String name) {
		return qSLVenuesDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveQSLVenues(QSLVenues o) {
		qSLVenuesDAO.persist(o);
	}

	@Override
	public void updateQSLVenues(QSLVenues o) {
		qSLVenuesDAO.merge(o);
	}
	
	@Override
	public List<QSLVenues> findAllQSLVenuesByIds(Integer[] cids) {
		return qSLVenuesDAO.findAllByIds(cids);
	}

	@Override
	public List<QSLVenues> findTopNVenues(Integer n) {
		
		return qSLVenuesDAO.findTopN(n);
	}

}
