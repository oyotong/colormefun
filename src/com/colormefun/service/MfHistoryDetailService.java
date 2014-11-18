package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfHistoryDetail;

public interface MfHistoryDetailService {
	
	public void saveMfHistoryDetail(MfHistoryDetail o);
	
	public void updateMfHistoryDetail(MfHistoryDetail o);
	
	public void deleteMfHistoryDetail(MfHistoryDetail o);
	
	public void deleteMfHistoryDetailByIds(String[] ids);
	
	public MfHistoryDetail getMfHistoryDetailById(Serializable id);
	
	public List<MfHistoryDetail> findAllMfHistoryDetail();
	
	public List<MfHistoryDetail> findAllMfHistoryDetailWithPage();
	
	public List<MfHistoryDetail> findAllMfHistoryDetailBySearchWithPage(MfHistoryDetail o);
	
	public Map findAllMfHistoryDetailByNameWithIdAndName(String name);
	
	public List<MfHistoryDetail> findAllMfHistoryDetailByIds(String[] cids);
	
}
