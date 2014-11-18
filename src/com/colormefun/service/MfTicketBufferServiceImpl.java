package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfTicketBufferPK;
import com.colormefun.entity.MfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfTicketBufferDAO;
import com.colormefun.entity.MfTicketBuffer;
import shop.common.context.ApplicationContext;
import shop.common.exception.ApplicationException;
import shop.common.util.StringUtils;

@Service
@Transactional
public class MfTicketBufferServiceImpl implements MfTicketBufferService {

	@Autowired()
	private MfTicketBufferDAO mfTicketBufferDAO;

	public MfTicketBufferDAO getMfTicketBufferDAO() {
		return mfTicketBufferDAO;
	}

	public void setMfTicketBufferDAO(MfTicketBufferDAO mfTicketBufferDAO) {
		this.mfTicketBufferDAO = mfTicketBufferDAO;
	}

	@Override
	public void deleteMfTicketBuffer(MfTicketBuffer o) {
		mfTicketBufferDAO.remove(o);
	}
	
	@Override
	public void deleteMfTicketBufferByIds(MfTicketBufferPK[] ids){
		mfTicketBufferDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfTicketBuffer> findAllMfTicketBuffer() {
		return mfTicketBufferDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfTicketBuffer> findAllMfTicketBufferWithPage(){
		return mfTicketBufferDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfTicketBuffer> findAllMfTicketBufferBySearchWithPage(MfTicketBuffer o){
		return mfTicketBufferDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfTicketBuffer getMfTicketBufferById(Serializable id) {
		return mfTicketBufferDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfTicketBufferByNameWithIdAndName(String name) {
		return mfTicketBufferDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfTicketBuffer(MfTicketBuffer o) {
		mfTicketBufferDAO.persist(o);
	}

	@Override
	public void updateMfTicketBuffer(MfTicketBuffer o) {
		mfTicketBufferDAO.merge(o);
	}
	
	@Override
	public List<MfTicketBuffer> findAllMfTicketBufferByIds(MfTicketBufferPK[] cids) {
		return mfTicketBufferDAO.findAllByIds(cids);
	}

    @Override
    public void addToTicketBuffer(MfTicketBuffer mfTicketBuffer) {
        MfUser currentUser = ApplicationContext.getContext().getCurrentMfUser();
        if(null != currentUser){
            mfTicketBuffer.setCreatedId(currentUser.getUserNo()+"");
            if(StringUtils.isNull(mfTicketBuffer.getMobilePhone())){
                mfTicketBuffer.setMobilePhone(currentUser.getMobilePhone());
            }
            if(StringUtils.isNull(mfTicketBuffer.getEmail())){
                mfTicketBuffer.setEmail(currentUser.getEmail());
            }
        }

        if(StringUtils.isNull(mfTicketBuffer.getMobilePhone()) || StringUtils.isNull(mfTicketBuffer.getEmail())){
           throw new ApplicationException("为了我们更好的联系您，请务必填写手机号和电子邮件地址");
        }

        MfTicketBuffer old = mfTicketBufferDAO.igetByQL("select t from MfTicketBuffer t where t.caseNo=? and t.mobilePhone=?"
                , mfTicketBuffer.getCaseNo(), mfTicketBuffer.getMobilePhone());

        if(null != old){
            throw new ApplicationException("您已经加入等候名单，不能重复加入。");
        }

        Long maxIndexNo = (Long)((Object)mfTicketBufferDAO.igetByQL("select max(t.indexNo) from MfTicketBuffer t where t.caseNo=?", mfTicketBuffer.getCaseNo()));
        if(null == maxIndexNo) maxIndexNo = 0L;
        mfTicketBuffer.setIndexNo(maxIndexNo.intValue()+1);
        mfTicketBuffer.setCreatedDate(new Date());

        this.mfTicketBufferDAO.persist(mfTicketBuffer);

    }

}
