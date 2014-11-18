package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfOrderDetail;

public interface MfOrderDetailService {
	
	public void saveMfOrderDetail(MfOrderDetail o);
	
	public void updateMfOrderDetail(MfOrderDetail o);
	
	public void deleteMfOrderDetail(MfOrderDetail o);
	
	public void deleteMfOrderDetailByIds(String[] ids);
	
	public MfOrderDetail getMfOrderDetailById(Serializable id);
	
	public List<MfOrderDetail> findAllMfOrderDetail();
	
	public List<MfOrderDetail> findAllMfOrderDetailWithPage();
	
	public List<MfOrderDetail> findAllMfOrderDetailBySearchWithPage(MfOrderDetail o);
	
	public Map findAllMfOrderDetailByNameWithIdAndName(String name);
	
	public List<MfOrderDetail> findAllMfOrderDetailByIds(String[] cids);
	
}
