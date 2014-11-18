package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfHistoryDAO;
import com.colormefun.entity.MfHistory;

@Service
@Transactional
public class MfHistoryServiceImpl implements MfHistoryService {

	@Autowired()
	private MfHistoryDAO mfHistoryDAO;

	public MfHistoryDAO getMfHistoryDAO() {
		return mfHistoryDAO;
	}

	public void setMfHistoryDAO(MfHistoryDAO mfHistoryDAO) {
		this.mfHistoryDAO = mfHistoryDAO;
	}

	@Override
	public void deleteMfHistory(MfHistory o) {
		mfHistoryDAO.remove(o);
	}
	
	@Override
	public void deleteMfHistoryByIds(String[] ids){
		mfHistoryDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfHistory> findAllMfHistory() {
		return mfHistoryDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfHistory> findAllMfHistoryWithPage(){
		return mfHistoryDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfHistory> findAllMfHistoryBySearchWithPage(MfHistory o){
		return mfHistoryDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfHistory getMfHistoryById(Serializable id) {
		return mfHistoryDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfHistoryByNameWithIdAndName(String name) {
		return mfHistoryDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfHistory(MfHistory o) {
		mfHistoryDAO.persist(o);
	}

	@Override
	public void updateMfHistory(MfHistory o) {
		mfHistoryDAO.merge(o);
	}
	
	@Override
	public List<MfHistory> findAllMfHistoryByIds(String[] cids) {
		return mfHistoryDAO.findAllByIds(cids);
	}

}
