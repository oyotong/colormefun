package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfCartCouponPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfCartCouponDAO;
import com.colormefun.entity.MfCartCoupon;

@Service
@Transactional
public class MfCartCouponServiceImpl implements MfCartCouponService {

	@Autowired()
	private MfCartCouponDAO mfCartCouponDAO;

	public MfCartCouponDAO getMfCartCouponDAO() {
		return mfCartCouponDAO;
	}

	public void setMfCartCouponDAO(MfCartCouponDAO mfCartCouponDAO) {
		this.mfCartCouponDAO = mfCartCouponDAO;
	}

	@Override
	public void deleteMfCartCoupon(MfCartCoupon o) {
		mfCartCouponDAO.remove(o);
	}
	
	@Override
	public void deleteMfCartCouponByIds(MfCartCouponPK[] ids){
		mfCartCouponDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfCartCoupon> findAllMfCartCoupon() {
		return mfCartCouponDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCartCoupon> findAllMfCartCouponWithPage(){
		return mfCartCouponDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCartCoupon> findAllMfCartCouponBySearchWithPage(MfCartCoupon o){
		return mfCartCouponDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfCartCoupon getMfCartCouponById(Serializable id) {
		return mfCartCouponDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfCartCouponByNameWithIdAndName(String name) {
		return mfCartCouponDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfCartCoupon(MfCartCoupon o) {
		mfCartCouponDAO.persist(o);
	}

	@Override
	public void updateMfCartCoupon(MfCartCoupon o) {
		mfCartCouponDAO.merge(o);
	}
	
	@Override
	public List<MfCartCoupon> findAllMfCartCouponByIds(MfCartCouponPK[] ids) {
		return mfCartCouponDAO.findAllByIds(ids);
	}

}
