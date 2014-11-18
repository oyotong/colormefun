package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfOrderCouponPKDAO;
import com.colormefun.entity.MfOrderCouponPK;

@Service
@Transactional
public class MfOrderCouponPKServiceImpl implements MfOrderCouponPKService {

	@Autowired()
	private MfOrderCouponPKDAO mfOrderCouponPKDAO;

	public MfOrderCouponPKDAO getMfOrderCouponPKDAO() {
		return mfOrderCouponPKDAO;
	}

	public void setMfOrderCouponPKDAO(MfOrderCouponPKDAO mfOrderCouponPKDAO) {
		this.mfOrderCouponPKDAO = mfOrderCouponPKDAO;
	}

	@Override
	public void deleteMfOrderCouponPK(MfOrderCouponPK o) {
		mfOrderCouponPKDAO.remove(o);
	}
	
	@Override
	public void deleteMfOrderCouponPKByIds(String[] ids){
		mfOrderCouponPKDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfOrderCouponPK> findAllMfOrderCouponPK() {
		return mfOrderCouponPKDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfOrderCouponPK> findAllMfOrderCouponPKWithPage(){
		return mfOrderCouponPKDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfOrderCouponPK> findAllMfOrderCouponPKBySearchWithPage(MfOrderCouponPK o){
		return mfOrderCouponPKDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfOrderCouponPK getMfOrderCouponPKById(Serializable id) {
		return mfOrderCouponPKDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfOrderCouponPKByNameWithIdAndName(String name) {
		return mfOrderCouponPKDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfOrderCouponPK(MfOrderCouponPK o) {
		mfOrderCouponPKDAO.persist(o);
	}

	@Override
	public void updateMfOrderCouponPK(MfOrderCouponPK o) {
		mfOrderCouponPKDAO.merge(o);
	}
	
	@Override
	public List<MfOrderCouponPK> findAllMfOrderCouponPKByIds(String[] cids) {
		return mfOrderCouponPKDAO.findAllByIds(cids);
	}

}
