package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfCartDetail;
import com.colormefun.entity.MfCartDetailPK;

public interface MfCartDetailService {
	
	public void saveMfCartDetail(MfCartDetail o);
	
	public void updateMfCartDetail(MfCartDetail o);
	
	public void deleteMfCartDetail(MfCartDetail o);
	
	public void deleteMfCartDetailByIds(MfCartDetailPK[] ids);
	
	public MfCartDetail getMfCartDetailById(Serializable id);
	
	public List<MfCartDetail> findAllMfCartDetail();
	
	public List<MfCartDetail> findAllMfCartDetailWithPage();
	
	public List<MfCartDetail> findAllMfCartDetailBySearchWithPage(MfCartDetail o);
	
	public Map findAllMfCartDetailByNameWithIdAndName(String name);
	
	public List<MfCartDetail> findAllMfCartDetailByIds(MfCartDetailPK[] cids);
	
}
