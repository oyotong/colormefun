package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfOrderDetailDAO;
import com.colormefun.entity.MfOrderDetail;

@Service
@Transactional
public class MfOrderDetailServiceImpl implements MfOrderDetailService {

	@Autowired()
	private MfOrderDetailDAO mfOrderDetailDAO;

	public MfOrderDetailDAO getMfOrderDetailDAO() {
		return mfOrderDetailDAO;
	}

	public void setMfOrderDetailDAO(MfOrderDetailDAO mfOrderDetailDAO) {
		this.mfOrderDetailDAO = mfOrderDetailDAO;
	}

	@Override
	public void deleteMfOrderDetail(MfOrderDetail o) {
		mfOrderDetailDAO.remove(o);
	}
	
	@Override
	public void deleteMfOrderDetailByIds(String[] ids){
		mfOrderDetailDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfOrderDetail> findAllMfOrderDetail() {
		return mfOrderDetailDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfOrderDetail> findAllMfOrderDetailWithPage(){
		return mfOrderDetailDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfOrderDetail> findAllMfOrderDetailBySearchWithPage(MfOrderDetail o){
		return mfOrderDetailDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfOrderDetail getMfOrderDetailById(Serializable id) {
		return mfOrderDetailDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfOrderDetailByNameWithIdAndName(String name) {
		return mfOrderDetailDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfOrderDetail(MfOrderDetail o) {
		mfOrderDetailDAO.persist(o);
	}

	@Override
	public void updateMfOrderDetail(MfOrderDetail o) {
		mfOrderDetailDAO.merge(o);
	}
	
	@Override
	public List<MfOrderDetail> findAllMfOrderDetailByIds(String[] cids) {
		return mfOrderDetailDAO.findAllByIds(cids);
	}

}
