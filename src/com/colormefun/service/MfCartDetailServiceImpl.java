package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfCartDetailPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfCartDetailDAO;
import com.colormefun.entity.MfCartDetail;

@Service
@Transactional
public class MfCartDetailServiceImpl implements MfCartDetailService {

	@Autowired()
	private MfCartDetailDAO mfCartDetailDAO;

	public MfCartDetailDAO getMfCartDetailDAO() {
		return mfCartDetailDAO;
	}

	public void setMfCartDetailDAO(MfCartDetailDAO mfCartDetailDAO) {
		this.mfCartDetailDAO = mfCartDetailDAO;
	}

	@Override
	public void deleteMfCartDetail(MfCartDetail o) {
		mfCartDetailDAO.remove(o);
	}
	
	@Override
	public void deleteMfCartDetailByIds(MfCartDetailPK[] ids){
		mfCartDetailDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfCartDetail> findAllMfCartDetail() {
		return mfCartDetailDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCartDetail> findAllMfCartDetailWithPage(){
		return mfCartDetailDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCartDetail> findAllMfCartDetailBySearchWithPage(MfCartDetail o){
		return mfCartDetailDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfCartDetail getMfCartDetailById(Serializable id) {
		return mfCartDetailDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfCartDetailByNameWithIdAndName(String name) {
		return mfCartDetailDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfCartDetail(MfCartDetail o) {
		mfCartDetailDAO.persist(o);
	}

	@Override
	public void updateMfCartDetail(MfCartDetail o) {
		mfCartDetailDAO.merge(o);
	}
	
	@Override
	public List<MfCartDetail> findAllMfCartDetailByIds(MfCartDetailPK[] cids) {
		return mfCartDetailDAO.findAllByIds(cids);
	}

}
