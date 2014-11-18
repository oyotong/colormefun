package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfCartCouponPKDAO;
import com.colormefun.entity.MfCartCouponPK;

@Service
@Transactional
public class MfCartCouponPKServiceImpl implements MfCartCouponPKService {

	@Autowired()
	private MfCartCouponPKDAO mfCartCouponPKDAO;

	public MfCartCouponPKDAO getMfCartCouponPKDAO() {
		return mfCartCouponPKDAO;
	}

	public void setMfCartCouponPKDAO(MfCartCouponPKDAO mfCartCouponPKDAO) {
		this.mfCartCouponPKDAO = mfCartCouponPKDAO;
	}

	@Override
	public void deleteMfCartCouponPK(MfCartCouponPK o) {
		mfCartCouponPKDAO.remove(o);
	}
	
	@Override
	public void deleteMfCartCouponPKByIds(Integer[] ids){
		mfCartCouponPKDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfCartCouponPK> findAllMfCartCouponPK() {
		return mfCartCouponPKDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCartCouponPK> findAllMfCartCouponPKWithPage(){
		return mfCartCouponPKDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCartCouponPK> findAllMfCartCouponPKBySearchWithPage(MfCartCouponPK o){
		return mfCartCouponPKDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfCartCouponPK getMfCartCouponPKById(Serializable id) {
		return mfCartCouponPKDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfCartCouponPKByNameWithIdAndName(String name) {
		return mfCartCouponPKDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfCartCouponPK(MfCartCouponPK o) {
		mfCartCouponPKDAO.persist(o);
	}

	@Override
	public void updateMfCartCouponPK(MfCartCouponPK o) {
		mfCartCouponPKDAO.merge(o);
	}
	
	@Override
	public List<MfCartCouponPK> findAllMfCartCouponPKByIds(Integer[] cids) {
		return mfCartCouponPKDAO.findAllByIds(cids);
	}

}
