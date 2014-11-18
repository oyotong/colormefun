package shop.company.service;

import java.io.Serializable;
import java.util.*;

import shop.company.entity.QSLProduct;

public interface QSLProductService {
	
	public void saveQSLProduct(QSLProduct o);
	
	public void updateQSLProduct(QSLProduct o);
	
	public void deleteQSLProduct(QSLProduct o);
	
	public void deleteQSLProductByIds(Integer[] ids);
	
	public QSLProduct getQSLProductById(Serializable id);
	
	public List<QSLProduct> findAllQSLProduct();
	
	public List<QSLProduct> findAllQSLProductWithPage();
	
	public List<QSLProduct> findAllQSLProductBySearchWithPage(QSLProduct o);
	
	public Map findAllQSLProductByNameWithIdAndName(String name);
	
	public List<QSLProduct> findAllQSLProductByIds(Integer[] cids);

	List<QSLProduct> findTopNProduct(Integer n);
	
}
