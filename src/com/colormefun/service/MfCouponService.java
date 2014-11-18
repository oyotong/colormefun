package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfCoupon;

public interface MfCouponService {
	
	public void saveMfCoupon(MfCoupon o);
	
	public void updateMfCoupon(MfCoupon o);
	
	public void deleteMfCoupon(MfCoupon o);
	
	public void deleteMfCouponByIds(Integer[] ids);
	
	public MfCoupon getMfCouponById(Serializable id);
	
	public List<MfCoupon> findAllMfCoupon();
	
	public List<MfCoupon> findAllMfCouponWithPage();
	
	public List<MfCoupon> findAllMfCouponBySearchWithPage(MfCoupon o);
	
	public Map findAllMfCouponByNameWithIdAndName(String name);
	
	public List<MfCoupon> findAllMfCouponByIds(Integer[] cids);

    void createMfCoupon(MfCoupon coupon);

    void saveAllMfCoupon(List<MfCoupon> list);

    MfCoupon getMfCouponByCouponNo(String couponNo);

    List<MfCoupon> findAllMfCouponByUserNoAndStatus(Integer userNo, String locked);
}
