package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfTicketBufferPKDAO;
import com.colormefun.entity.MfTicketBufferPK;

@Service
@Transactional
public class MfTicketBufferPKServiceImpl implements MfTicketBufferPKService {

	@Autowired()
	private MfTicketBufferPKDAO mfTicketBufferPKDAO;

	public MfTicketBufferPKDAO getMfTicketBufferPKDAO() {
		return mfTicketBufferPKDAO;
	}

	public void setMfTicketBufferPKDAO(MfTicketBufferPKDAO mfTicketBufferPKDAO) {
		this.mfTicketBufferPKDAO = mfTicketBufferPKDAO;
	}

	@Override
	public void deleteMfTicketBufferPK(MfTicketBufferPK o) {
		mfTicketBufferPKDAO.remove(o);
	}
	
	@Override
	public void deleteMfTicketBufferPKByIds(MfTicketBufferPK[] ids){
		mfTicketBufferPKDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfTicketBufferPK> findAllMfTicketBufferPK() {
		return mfTicketBufferPKDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfTicketBufferPK> findAllMfTicketBufferPKWithPage(){
		return mfTicketBufferPKDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfTicketBufferPK> findAllMfTicketBufferPKBySearchWithPage(MfTicketBufferPK o){
		return mfTicketBufferPKDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfTicketBufferPK getMfTicketBufferPKById(Serializable id) {
		return mfTicketBufferPKDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfTicketBufferPKByNameWithIdAndName(String name) {
		return mfTicketBufferPKDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfTicketBufferPK(MfTicketBufferPK o) {
		mfTicketBufferPKDAO.persist(o);
	}

	@Override
	public void updateMfTicketBufferPK(MfTicketBufferPK o) {
		mfTicketBufferPKDAO.merge(o);
	}
	
	@Override
	public List<MfTicketBufferPK> findAllMfTicketBufferPKByIds(MfTicketBufferPK[] cids) {
		return mfTicketBufferPKDAO.findAllByIds(cids);
	}

}
