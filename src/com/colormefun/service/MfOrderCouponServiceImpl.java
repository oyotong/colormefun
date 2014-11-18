package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfOrderCouponDAO;
import com.colormefun.entity.MfOrderCoupon;

@Service
@Transactional
public class MfOrderCouponServiceImpl implements MfOrderCouponService {

	@Autowired()
	private MfOrderCouponDAO mfOrderCouponDAO;

	public MfOrderCouponDAO getMfOrderCouponDAO() {
		return mfOrderCouponDAO;
	}

	public void setMfOrderCouponDAO(MfOrderCouponDAO mfOrderCouponDAO) {
		this.mfOrderCouponDAO = mfOrderCouponDAO;
	}

	@Override
	public void deleteMfOrderCoupon(MfOrderCoupon o) {
		mfOrderCouponDAO.remove(o);
	}
	
	@Override
	public void deleteMfOrderCouponByIds(String[] ids){
		mfOrderCouponDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfOrderCoupon> findAllMfOrderCoupon() {
		return mfOrderCouponDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfOrderCoupon> findAllMfOrderCouponWithPage(){
		return mfOrderCouponDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfOrderCoupon> findAllMfOrderCouponBySearchWithPage(MfOrderCoupon o){
		return mfOrderCouponDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfOrderCoupon getMfOrderCouponById(Serializable id) {
		return mfOrderCouponDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfOrderCouponByNameWithIdAndName(String name) {
		return mfOrderCouponDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfOrderCoupon(MfOrderCoupon o) {
		mfOrderCouponDAO.persist(o);
	}

	@Override
	public void updateMfOrderCoupon(MfOrderCoupon o) {
		mfOrderCouponDAO.merge(o);
	}
	
	@Override
	public List<MfOrderCoupon> findAllMfOrderCouponByIds(String[] cids) {
		return mfOrderCouponDAO.findAllByIds(cids);
	}

}
