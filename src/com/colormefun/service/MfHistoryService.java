package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfHistory;

public interface MfHistoryService {
	
	public void saveMfHistory(MfHistory o);
	
	public void updateMfHistory(MfHistory o);
	
	public void deleteMfHistory(MfHistory o);
	
	public void deleteMfHistoryByIds(String[] ids);
	
	public MfHistory getMfHistoryById(Serializable id);
	
	public List<MfHistory> findAllMfHistory();
	
	public List<MfHistory> findAllMfHistoryWithPage();
	
	public List<MfHistory> findAllMfHistoryBySearchWithPage(MfHistory o);
	
	public Map findAllMfHistoryByNameWithIdAndName(String name);
	
	public List<MfHistory> findAllMfHistoryByIds(String[] cids);
	
}
