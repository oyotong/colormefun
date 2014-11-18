package shop.company.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.QSLNewsDAO;
import shop.company.entity.QSLNews;

@Service(value="newsServiceImpl")
@Transactional
public class QSLNewsServiceImpl implements QSLNewsService {

	@Autowired()
	private QSLNewsDAO qSLNewsDAO;

	public QSLNewsDAO getQSLNewsDAO() {
		return qSLNewsDAO;
	}

	public void setQSLNewsDAO(QSLNewsDAO qSLNewsDAO) {
		this.qSLNewsDAO = qSLNewsDAO;
	}

	@Override
	public void deleteQSLNews(QSLNews o) {
		qSLNewsDAO.remove(o);
	}
	
	@Override
	public void deleteQSLNewsByIds(Integer[] ids){
		qSLNewsDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QSLNews> findAllQSLNews() {
		return qSLNewsDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLNews> findAllQSLNewsWithPage(){
		return qSLNewsDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLNews> findAllQSLNewsBySearchWithPage(QSLNews o){
		return qSLNewsDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public QSLNews getQSLNewsById(Serializable id) {
		return qSLNewsDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllQSLNewsByNameWithIdAndName(String name) {
		return qSLNewsDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveQSLNews(QSLNews o) {
		qSLNewsDAO.persist(o);
	}

	@Override
	public void updateQSLNews(QSLNews o) {
		qSLNewsDAO.merge(o);
	}
	
	@Override
	public List<QSLNews> findAllQSLNewsByIds(Integer[] cids) {
		return qSLNewsDAO.findAllByIds(cids);
	}
	
	@Override
	public List<QSLNews> findTopNNews(String type,Integer n) {
		return qSLNewsDAO.findTopNNews(type, n);
	}

	@Override
	@Transactional(readOnly = true)
	public QSLNews getContact() {
		List<QSLNews> list = qSLNewsDAO.findTopNNews("contact", 1);
		QSLNews news = (null != list && list.size()>0)?list.get(0):null;
		return news;
	}

	@Override
	@Transactional(readOnly = true)
	public QSLNews getJoin() {
		List<QSLNews> list = qSLNewsDAO.findTopNNews("join", 1);
		QSLNews news = (null != list && list.size()>0)?list.get(0):null;
		return news;
	}

}
