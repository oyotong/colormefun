package shop.company.service;

import java.io.Serializable;
import java.util.*;

import shop.company.entity.QSLCoach;

public interface QSLCoachService {
	
	public void saveQSLCoach(QSLCoach o);
	
	public void updateQSLCoach(QSLCoach o);
	
	public void deleteQSLCoach(QSLCoach o);
	
	public void deleteQSLCoachByIds(Integer[] ids);
	
	public QSLCoach getQSLCoachById(Serializable id);
	
	public List<QSLCoach> findAllQSLCoach();
	
	public List<QSLCoach> findAllQSLCoachWithPage();
	
	public List<QSLCoach> findAllQSLCoachBySearchWithPage(QSLCoach o);
	
	public Map findAllQSLCoachByNameWithIdAndName(String name);
	
	public List<QSLCoach> findAllQSLCoachByIds(Integer[] cids);
	
	public List<QSLCoach> findTopNCoach(Integer n);
	
}
