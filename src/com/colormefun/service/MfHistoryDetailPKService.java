package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfHistoryDetailPK;

public interface MfHistoryDetailPKService {
	
	public void saveMfHistoryDetailPK(MfHistoryDetailPK o);
	
	public void updateMfHistoryDetailPK(MfHistoryDetailPK o);
	
	public void deleteMfHistoryDetailPK(MfHistoryDetailPK o);
	
	public void deleteMfHistoryDetailPKByIds(String[] ids);
	
	public MfHistoryDetailPK getMfHistoryDetailPKById(Serializable id);
	
	public List<MfHistoryDetailPK> findAllMfHistoryDetailPK();
	
	public List<MfHistoryDetailPK> findAllMfHistoryDetailPKWithPage();
	
	public List<MfHistoryDetailPK> findAllMfHistoryDetailPKBySearchWithPage(MfHistoryDetailPK o);
	
	public Map findAllMfHistoryDetailPKByNameWithIdAndName(String name);
	
	public List<MfHistoryDetailPK> findAllMfHistoryDetailPKByIds(String[] cids);
	
}
