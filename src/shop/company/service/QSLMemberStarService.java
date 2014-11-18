package shop.company.service;

import java.io.Serializable;
import java.util.*;

import shop.company.entity.QSLMemberStar;

public interface QSLMemberStarService {
	
	public void saveQSLMemberStar(QSLMemberStar o);
	
	public void updateQSLMemberStar(QSLMemberStar o);
	
	public void deleteQSLMemberStar(QSLMemberStar o);
	
	public void deleteQSLMemberStarByIds(Integer[] ids);
	
	public QSLMemberStar getQSLMemberStarById(Serializable id);
	
	public List<QSLMemberStar> findAllQSLMemberStar();
	
	public List<QSLMemberStar> findAllQSLMemberStarWithPage();
	
	public List<QSLMemberStar> findAllQSLMemberStarBySearchWithPage(QSLMemberStar o);
	
	public Map findAllQSLMemberStarByNameWithIdAndName(String name);
	
	public List<QSLMemberStar> findAllQSLMemberStarByIds(Integer[] cids);
	
	public List<QSLMemberStar> findTopNStar(Integer n);
	
}
