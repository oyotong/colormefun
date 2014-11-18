package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfCartCoupon;
import com.colormefun.entity.MfCartCouponPK;

public interface MfCartCouponService {
	
	public void saveMfCartCoupon(MfCartCoupon o);
	
	public void updateMfCartCoupon(MfCartCoupon o);
	
	public void deleteMfCartCoupon(MfCartCoupon o);
	
	public void deleteMfCartCouponByIds(MfCartCouponPK[] ids);
	
	public MfCartCoupon getMfCartCouponById(Serializable id);
	
	public List<MfCartCoupon> findAllMfCartCoupon();
	
	public List<MfCartCoupon> findAllMfCartCouponWithPage();
	
	public List<MfCartCoupon> findAllMfCartCouponBySearchWithPage(MfCartCoupon o);
	
	public Map findAllMfCartCouponByNameWithIdAndName(String name);
	
	public List<MfCartCoupon> findAllMfCartCouponByIds(MfCartCouponPK[] ids);
	
}
