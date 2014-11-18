package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfOrderDetailPK;

public interface MfOrderDetailPKService {
	
	public void saveMfOrderDetailPK(MfOrderDetailPK o);
	
	public void updateMfOrderDetailPK(MfOrderDetailPK o);
	
	public void deleteMfOrderDetailPK(MfOrderDetailPK o);
	
	public void deleteMfOrderDetailPKByIds(String[] ids);
	
	public MfOrderDetailPK getMfOrderDetailPKById(Serializable id);
	
	public List<MfOrderDetailPK> findAllMfOrderDetailPK();
	
	public List<MfOrderDetailPK> findAllMfOrderDetailPKWithPage();
	
	public List<MfOrderDetailPK> findAllMfOrderDetailPKBySearchWithPage(MfOrderDetailPK o);
	
	public Map findAllMfOrderDetailPKByNameWithIdAndName(String name);
	
	public List<MfOrderDetailPK> findAllMfOrderDetailPKByIds(String[] cids);
	
}
