package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfOrderCoupon;

public interface MfOrderCouponService {
	
	public void saveMfOrderCoupon(MfOrderCoupon o);
	
	public void updateMfOrderCoupon(MfOrderCoupon o);
	
	public void deleteMfOrderCoupon(MfOrderCoupon o);
	
	public void deleteMfOrderCouponByIds(String[] ids);
	
	public MfOrderCoupon getMfOrderCouponById(Serializable id);
	
	public List<MfOrderCoupon> findAllMfOrderCoupon();
	
	public List<MfOrderCoupon> findAllMfOrderCouponWithPage();
	
	public List<MfOrderCoupon> findAllMfOrderCouponBySearchWithPage(MfOrderCoupon o);
	
	public Map findAllMfOrderCouponByNameWithIdAndName(String name);
	
	public List<MfOrderCoupon> findAllMfOrderCouponByIds(String[] cids);
	
}
