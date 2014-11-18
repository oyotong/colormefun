package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfMonthlyReportPKDAO;
import com.colormefun.entity.MfMonthlyReportPK;

@Service
@Transactional
public class MfMonthlyReportPKServiceImpl implements MfMonthlyReportPKService {

	@Autowired()
	private MfMonthlyReportPKDAO mfMonthlyReportPKDAO;

	public MfMonthlyReportPKDAO getMfMonthlyReportPKDAO() {
		return mfMonthlyReportPKDAO;
	}

	public void setMfMonthlyReportPKDAO(MfMonthlyReportPKDAO mfMonthlyReportPKDAO) {
		this.mfMonthlyReportPKDAO = mfMonthlyReportPKDAO;
	}

	@Override
	public void deleteMfMonthlyReportPK(MfMonthlyReportPK o) {
		mfMonthlyReportPKDAO.remove(o);
	}
	
	@Override
	public void deleteMfMonthlyReportPKByIds(String[] ids){
		mfMonthlyReportPKDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfMonthlyReportPK> findAllMfMonthlyReportPK() {
		return mfMonthlyReportPKDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfMonthlyReportPK> findAllMfMonthlyReportPKWithPage(){
		return mfMonthlyReportPKDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfMonthlyReportPK> findAllMfMonthlyReportPKBySearchWithPage(MfMonthlyReportPK o){
		return mfMonthlyReportPKDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfMonthlyReportPK getMfMonthlyReportPKById(Serializable id) {
		return mfMonthlyReportPKDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfMonthlyReportPKByNameWithIdAndName(String name) {
		return mfMonthlyReportPKDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfMonthlyReportPK(MfMonthlyReportPK o) {
		mfMonthlyReportPKDAO.persist(o);
	}

	@Override
	public void updateMfMonthlyReportPK(MfMonthlyReportPK o) {
		mfMonthlyReportPKDAO.merge(o);
	}
	
	@Override
	public List<MfMonthlyReportPK> findAllMfMonthlyReportPKByIds(String[] cids) {
		return mfMonthlyReportPKDAO.findAllByIds(cids);
	}

}
