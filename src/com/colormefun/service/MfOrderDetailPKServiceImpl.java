package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfOrderDetailPKDAO;
import com.colormefun.entity.MfOrderDetailPK;

@Service
@Transactional
public class MfOrderDetailPKServiceImpl implements MfOrderDetailPKService {

	@Autowired()
	private MfOrderDetailPKDAO mfOrderDetailPKDAO;

	public MfOrderDetailPKDAO getMfOrderDetailPKDAO() {
		return mfOrderDetailPKDAO;
	}

	public void setMfOrderDetailPKDAO(MfOrderDetailPKDAO mfOrderDetailPKDAO) {
		this.mfOrderDetailPKDAO = mfOrderDetailPKDAO;
	}

	@Override
	public void deleteMfOrderDetailPK(MfOrderDetailPK o) {
		mfOrderDetailPKDAO.remove(o);
	}
	
	@Override
	public void deleteMfOrderDetailPKByIds(String[] ids){
		mfOrderDetailPKDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfOrderDetailPK> findAllMfOrderDetailPK() {
		return mfOrderDetailPKDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfOrderDetailPK> findAllMfOrderDetailPKWithPage(){
		return mfOrderDetailPKDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfOrderDetailPK> findAllMfOrderDetailPKBySearchWithPage(MfOrderDetailPK o){
		return mfOrderDetailPKDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfOrderDetailPK getMfOrderDetailPKById(Serializable id) {
		return mfOrderDetailPKDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfOrderDetailPKByNameWithIdAndName(String name) {
		return mfOrderDetailPKDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfOrderDetailPK(MfOrderDetailPK o) {
		mfOrderDetailPKDAO.persist(o);
	}

	@Override
	public void updateMfOrderDetailPK(MfOrderDetailPK o) {
		mfOrderDetailPKDAO.merge(o);
	}
	
	@Override
	public List<MfOrderDetailPK> findAllMfOrderDetailPKByIds(String[] cids) {
		return mfOrderDetailPKDAO.findAllByIds(cids);
	}

}
