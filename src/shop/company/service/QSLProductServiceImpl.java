package shop.company.service;

import java.io.Serializable;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.QSLProductDAO;
import shop.company.entity.QSLNews;
import shop.company.entity.QSLProduct;

@Service(value="productServiceImpl")
@Transactional
public class QSLProductServiceImpl implements QSLProductService {

	@Autowired()
	private QSLProductDAO qSLProductDAO;

	public QSLProductDAO getQSLProductDAO() {
		return qSLProductDAO;
	}

	public void setQSLProductDAO(QSLProductDAO qSLProductDAO) {
		this.qSLProductDAO = qSLProductDAO;
	}

	@Override
	public void deleteQSLProduct(QSLProduct o) {
		qSLProductDAO.remove(o);
	}
	
	@Override
	public void deleteQSLProductByIds(Integer[] ids){
		qSLProductDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QSLProduct> findAllQSLProduct() {
		return qSLProductDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLProduct> findAllQSLProductWithPage(){
		return qSLProductDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<QSLProduct> findAllQSLProductBySearchWithPage(QSLProduct o){
		return qSLProductDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public QSLProduct getQSLProductById(Serializable id) {
		return qSLProductDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllQSLProductByNameWithIdAndName(String name) {
		return qSLProductDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveQSLProduct(QSLProduct o) {
		qSLProductDAO.persist(o);
	}

	@Override
	public void updateQSLProduct(QSLProduct o) {
		qSLProductDAO.merge(o);
	}
	
	@Override
	public List<QSLProduct> findAllQSLProductByIds(Integer[] cids) {
		return qSLProductDAO.findAllByIds(cids);
	}
	
	@Override
	public List<QSLProduct> findTopNProduct(Integer n) {
		return qSLProductDAO.findTopNProduct(n);
	}

}
