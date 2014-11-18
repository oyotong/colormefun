package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfHistoryDetailDAO;
import com.colormefun.entity.MfHistoryDetail;

@Service
@Transactional
public class MfHistoryDetailServiceImpl implements MfHistoryDetailService {

	@Autowired()
	private MfHistoryDetailDAO mfHistoryDetailDAO;

	public MfHistoryDetailDAO getMfHistoryDetailDAO() {
		return mfHistoryDetailDAO;
	}

	public void setMfHistoryDetailDAO(MfHistoryDetailDAO mfHistoryDetailDAO) {
		this.mfHistoryDetailDAO = mfHistoryDetailDAO;
	}

	@Override
	public void deleteMfHistoryDetail(MfHistoryDetail o) {
		mfHistoryDetailDAO.remove(o);
	}
	
	@Override
	public void deleteMfHistoryDetailByIds(String[] ids){
		mfHistoryDetailDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfHistoryDetail> findAllMfHistoryDetail() {
		return mfHistoryDetailDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfHistoryDetail> findAllMfHistoryDetailWithPage(){
		return mfHistoryDetailDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfHistoryDetail> findAllMfHistoryDetailBySearchWithPage(MfHistoryDetail o){
		return mfHistoryDetailDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfHistoryDetail getMfHistoryDetailById(Serializable id) {
		return mfHistoryDetailDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfHistoryDetailByNameWithIdAndName(String name) {
		return mfHistoryDetailDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfHistoryDetail(MfHistoryDetail o) {
		mfHistoryDetailDAO.persist(o);
	}

	@Override
	public void updateMfHistoryDetail(MfHistoryDetail o) {
		mfHistoryDetailDAO.merge(o);
	}
	
	@Override
	public List<MfHistoryDetail> findAllMfHistoryDetailByIds(String[] cids) {
		return mfHistoryDetailDAO.findAllByIds(cids);
	}

}
