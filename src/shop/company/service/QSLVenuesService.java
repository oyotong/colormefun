package shop.company.service;

import java.io.Serializable;
import java.util.*;

import shop.company.entity.QSLVenues;

public interface QSLVenuesService {
	
	public void saveQSLVenues(QSLVenues o);
	
	public void updateQSLVenues(QSLVenues o);
	
	public void deleteQSLVenues(QSLVenues o);
	
	public void deleteQSLVenuesByIds(Integer[] ids);
	
	public QSLVenues getQSLVenuesById(Serializable id);
	
	public List<QSLVenues> findAllQSLVenues();
	
	public List<QSLVenues> findAllQSLVenuesWithPage();
	
	public List<QSLVenues> findAllQSLVenuesBySearchWithPage(QSLVenues o);
	
	public Map findAllQSLVenuesByNameWithIdAndName(String name);
	
	public List<QSLVenues> findAllQSLVenuesByIds(Integer[] cids);
	
	public List<QSLVenues> findTopNVenues(Integer n);
	
}
