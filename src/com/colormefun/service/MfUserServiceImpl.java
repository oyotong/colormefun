package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfUserDAO;
import com.colormefun.entity.MfUser;
import shop.common.util.StringUtils;

@Service
@Transactional
public class MfUserServiceImpl implements MfUserService {

	@Autowired()
	private MfUserDAO mfUserDAO;

	public MfUserDAO getMfUserDAO() {
		return mfUserDAO;
	}

	public void setMfUserDAO(MfUserDAO mfUserDAO) {
		this.mfUserDAO = mfUserDAO;
	}

	@Override
	public void deleteMfUser(MfUser o) {
		mfUserDAO.remove(o);
	}
	
	@Override
	public void deleteMfUserByIds(Integer[] ids){
		mfUserDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfUser> findAllMfUser() {
		return mfUserDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfUser> findAllMfUserWithPage(){
		return mfUserDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfUser> findAllMfUserBySearchWithPage(MfUser o){
		return mfUserDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfUser getMfUserById(Serializable id) {
		return mfUserDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfUserByNameWithIdAndName(String name) {
		return mfUserDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfUser(MfUser o) {
		mfUserDAO.persist(o);
	}

	@Override
	public void updateMfUser(MfUser o) {
		mfUserDAO.merge(o);
	}
	
	@Override
	public List<MfUser> findAllMfUserByIds(Integer[] cids) {
		return mfUserDAO.findAllByIds(cids);
	}

    @Override
    public MfUser resetPwdById(Integer cid) {
        MfUser user = getMfUserById(cid);
        if(null == user){
            return null;
        }

        String password = StringUtils.getPassword(8);
        String md5Password = StringUtils.toMD5(password);
        user.setPassword(md5Password);
        user.setPassword2(password);
        //TODO: email password to user
        return user;
    }

    @Override
    public MfUser login(MfUser user) {
        if(user.getMobilePhone() == null || user.getPassword() == null ||
                user.getMobilePhone().trim().length() == 0 ||
                user.getPassword().trim().length() == 0){
            return null;
        }
        List<MfUser> list = findAllMfUserBySearchWithPage(user);
        if(null == list || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public MfUser getMfUserByMobilePhone(String mobilePhone) {

        if(mobilePhone == null || mobilePhone.trim().length() == 0){
            return null;
        }

        MfUser user = new MfUser();
        user.setMobilePhone(mobilePhone);
        List<MfUser> list = findAllMfUserBySearchWithPage(user);
        if(null == list || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

}
