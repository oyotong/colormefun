package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfCartDetailPKDAO;
import com.colormefun.entity.MfCartDetailPK;

@Service
@Transactional
public class MfCartDetailPKServiceImpl implements MfCartDetailPKService {

	@Autowired()
	private MfCartDetailPKDAO mfCartDetailPKDAO;

	public MfCartDetailPKDAO getMfCartDetailPKDAO() {
		return mfCartDetailPKDAO;
	}

	public void setMfCartDetailPKDAO(MfCartDetailPKDAO mfCartDetailPKDAO) {
		this.mfCartDetailPKDAO = mfCartDetailPKDAO;
	}

	@Override
	public void deleteMfCartDetailPK(MfCartDetailPK o) {
		mfCartDetailPKDAO.remove(o);
	}
	
	@Override
	public void deleteMfCartDetailPKByIds(Integer[] ids){
		mfCartDetailPKDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfCartDetailPK> findAllMfCartDetailPK() {
		return mfCartDetailPKDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCartDetailPK> findAllMfCartDetailPKWithPage(){
		return mfCartDetailPKDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCartDetailPK> findAllMfCartDetailPKBySearchWithPage(MfCartDetailPK o){
		return mfCartDetailPKDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfCartDetailPK getMfCartDetailPKById(Serializable id) {
		return mfCartDetailPKDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfCartDetailPKByNameWithIdAndName(String name) {
		return mfCartDetailPKDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfCartDetailPK(MfCartDetailPK o) {
		mfCartDetailPKDAO.persist(o);
	}

	@Override
	public void updateMfCartDetailPK(MfCartDetailPK o) {
		mfCartDetailPKDAO.merge(o);
	}
	
	@Override
	public List<MfCartDetailPK> findAllMfCartDetailPKByIds(Integer[] cids) {
		return mfCartDetailPKDAO.findAllByIds(cids);
	}

}
