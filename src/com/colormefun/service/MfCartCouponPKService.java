package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfCartCouponPK;

public interface MfCartCouponPKService {
	
	public void saveMfCartCouponPK(MfCartCouponPK o);
	
	public void updateMfCartCouponPK(MfCartCouponPK o);
	
	public void deleteMfCartCouponPK(MfCartCouponPK o);
	
	public void deleteMfCartCouponPKByIds(Integer[] ids);
	
	public MfCartCouponPK getMfCartCouponPKById(Serializable id);
	
	public List<MfCartCouponPK> findAllMfCartCouponPK();
	
	public List<MfCartCouponPK> findAllMfCartCouponPKWithPage();
	
	public List<MfCartCouponPK> findAllMfCartCouponPKBySearchWithPage(MfCartCouponPK o);
	
	public Map findAllMfCartCouponPKByNameWithIdAndName(String name);
	
	public List<MfCartCouponPK> findAllMfCartCouponPKByIds(Integer[] cids);
	
}
