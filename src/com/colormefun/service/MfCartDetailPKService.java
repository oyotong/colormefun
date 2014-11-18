package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfCartDetailPK;

public interface MfCartDetailPKService {
	
	public void saveMfCartDetailPK(MfCartDetailPK o);
	
	public void updateMfCartDetailPK(MfCartDetailPK o);
	
	public void deleteMfCartDetailPK(MfCartDetailPK o);
	
	public void deleteMfCartDetailPKByIds(Integer[] ids);
	
	public MfCartDetailPK getMfCartDetailPKById(Serializable id);
	
	public List<MfCartDetailPK> findAllMfCartDetailPK();
	
	public List<MfCartDetailPK> findAllMfCartDetailPKWithPage();
	
	public List<MfCartDetailPK> findAllMfCartDetailPKBySearchWithPage(MfCartDetailPK o);
	
	public Map findAllMfCartDetailPKByNameWithIdAndName(String name);
	
	public List<MfCartDetailPK> findAllMfCartDetailPKByIds(Integer[] cids);
	
}
