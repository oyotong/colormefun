package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfUser;

public interface MfUserService {
	
	public void saveMfUser(MfUser o);
	
	public void updateMfUser(MfUser o);
	
	public void deleteMfUser(MfUser o);
	
	public void deleteMfUserByIds(Integer[] ids);
	
	public MfUser getMfUserById(Serializable id);
	
	public List<MfUser> findAllMfUser();
	
	public List<MfUser> findAllMfUserWithPage();
	
	public List<MfUser> findAllMfUserBySearchWithPage(MfUser o);
	
	public Map findAllMfUserByNameWithIdAndName(String name);
	
	public List<MfUser> findAllMfUserByIds(Integer[] cids);

    MfUser resetPwdById(Integer cid);

    MfUser login(MfUser user);

    MfUser getMfUserByMobilePhone(String mobilePhone);
}
