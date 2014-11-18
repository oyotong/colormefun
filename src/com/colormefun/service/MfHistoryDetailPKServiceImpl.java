package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfHistoryDetailPKDAO;
import com.colormefun.entity.MfHistoryDetailPK;

@Service
@Transactional
public class MfHistoryDetailPKServiceImpl implements MfHistoryDetailPKService {

	@Autowired()
	private MfHistoryDetailPKDAO mfHistoryDetailPKDAO;

	public MfHistoryDetailPKDAO getMfHistoryDetailPKDAO() {
		return mfHistoryDetailPKDAO;
	}

	public void setMfHistoryDetailPKDAO(MfHistoryDetailPKDAO mfHistoryDetailPKDAO) {
		this.mfHistoryDetailPKDAO = mfHistoryDetailPKDAO;
	}

	@Override
	public void deleteMfHistoryDetailPK(MfHistoryDetailPK o) {
		mfHistoryDetailPKDAO.remove(o);
	}
	
	@Override
	public void deleteMfHistoryDetailPKByIds(String[] ids){
		mfHistoryDetailPKDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfHistoryDetailPK> findAllMfHistoryDetailPK() {
		return mfHistoryDetailPKDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfHistoryDetailPK> findAllMfHistoryDetailPKWithPage(){
		return mfHistoryDetailPKDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfHistoryDetailPK> findAllMfHistoryDetailPKBySearchWithPage(MfHistoryDetailPK o){
		return mfHistoryDetailPKDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfHistoryDetailPK getMfHistoryDetailPKById(Serializable id) {
		return mfHistoryDetailPKDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfHistoryDetailPKByNameWithIdAndName(String name) {
		return mfHistoryDetailPKDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfHistoryDetailPK(MfHistoryDetailPK o) {
		mfHistoryDetailPKDAO.persist(o);
	}

	@Override
	public void updateMfHistoryDetailPK(MfHistoryDetailPK o) {
		mfHistoryDetailPKDAO.merge(o);
	}
	
	@Override
	public List<MfHistoryDetailPK> findAllMfHistoryDetailPKByIds(String[] cids) {
		return mfHistoryDetailPKDAO.findAllByIds(cids);
	}

}
