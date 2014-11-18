package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfOrderCouponPK;

public interface MfOrderCouponPKService {
	
	public void saveMfOrderCouponPK(MfOrderCouponPK o);
	
	public void updateMfOrderCouponPK(MfOrderCouponPK o);
	
	public void deleteMfOrderCouponPK(MfOrderCouponPK o);
	
	public void deleteMfOrderCouponPKByIds(String[] ids);
	
	public MfOrderCouponPK getMfOrderCouponPKById(Serializable id);
	
	public List<MfOrderCouponPK> findAllMfOrderCouponPK();
	
	public List<MfOrderCouponPK> findAllMfOrderCouponPKWithPage();
	
	public List<MfOrderCouponPK> findAllMfOrderCouponPKBySearchWithPage(MfOrderCouponPK o);
	
	public Map findAllMfOrderCouponPKByNameWithIdAndName(String name);
	
	public List<MfOrderCouponPK> findAllMfOrderCouponPKByIds(String[] cids);
	
}
