package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfVipCaseDAO;
import com.colormefun.entity.MfVipCase;

@Service
@Transactional
public class MfVipCaseServiceImpl implements MfVipCaseService {

	@Autowired()
	private MfVipCaseDAO mfVipCaseDAO;

	public MfVipCaseDAO getMfVipCaseDAO() {
		return mfVipCaseDAO;
	}

	public void setMfVipCaseDAO(MfVipCaseDAO mfVipCaseDAO) {
		this.mfVipCaseDAO = mfVipCaseDAO;
	}

	@Override
	public void deleteMfVipCase(MfVipCase o) {
		mfVipCaseDAO.remove(o);
	}
	
	@Override
	public void deleteMfVipCaseByIds(Integer[] ids){
		mfVipCaseDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfVipCase> findAllMfVipCase() {
		return mfVipCaseDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfVipCase> findAllMfVipCaseWithPage(){
		return mfVipCaseDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfVipCase> findAllMfVipCaseBySearchWithPage(MfVipCase o){
		return mfVipCaseDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfVipCase getMfVipCaseById(Serializable id) {
		return mfVipCaseDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfVipCaseByNameWithIdAndName(String name) {
		return mfVipCaseDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfVipCase(MfVipCase o) {
		mfVipCaseDAO.persist(o);
	}

	@Override
	public void updateMfVipCase(MfVipCase o) {
		mfVipCaseDAO.merge(o);
	}
	
	@Override
	public List<MfVipCase> findAllMfVipCaseByIds(Integer[] cids) {
		return mfVipCaseDAO.findAllByIds(cids);
	}

}
