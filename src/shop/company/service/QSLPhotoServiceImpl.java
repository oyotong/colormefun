package shop.company.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.QSLPhotoDAO;
import shop.company.entity.QSLPhoto;

@Service
@Transactional
public class QSLPhotoServiceImpl implements QSLPhotoService {

	@Autowired()
	private QSLPhotoDAO qSLPhotoDAO;

	public QSLPhotoDAO getQSLPhotoDAO() {
		return qSLPhotoDAO;
	}

	public void setQSLPhotoDAO(QSLPhotoDAO qSLPhotoDAO) {
		this.qSLPhotoDAO = qSLPhotoDAO;
	}

	@Override
	public void deleteQSLPhoto(QSLPhoto o) {
		qSLPhotoDAO.remove(o);
	}
	
	@Override
	public void deleteQSLPhotoByIds(Integer[] ids){
		qSLPhotoDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QSLPhoto> findAllQSLPhoto() {
		return qSLPhotoDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLPhoto> findAllQSLPhotoWithPage(){
		return qSLPhotoDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLPhoto> findAllQSLPhotoBySearchWithPage(QSLPhoto o){
		return qSLPhotoDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public QSLPhoto getQSLPhotoById(Serializable id) {
		return qSLPhotoDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllQSLPhotoByNameWithIdAndName(String name) {
		return qSLPhotoDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveQSLPhoto(QSLPhoto o) {
		qSLPhotoDAO.persist(o);
	}

	@Override
	public void updateQSLPhoto(QSLPhoto o) {
		qSLPhotoDAO.merge(o);
	}
	
	@Override
	public List<QSLPhoto> findAllQSLPhotoByIds(Integer[] cids) {
		return qSLPhotoDAO.findAllByIds(cids);
	}

}
