package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfCart;
import com.colormefun.entity.MfUser;

public interface MfCartService {
	
	public void saveMfCart(MfCart o);
	
	public void updateMfCart(MfCart o);
	
	public void deleteMfCart(MfCart o);
	
	public void deleteMfCartByIds(Integer[] ids);
	
	public MfCart getMfCartById(Serializable id);
	
	public List<MfCart> findAllMfCart();
	
	public List<MfCart> findAllMfCartWithPage();
	
	public List<MfCart> findAllMfCartBySearchWithPage(MfCart o);
	
	public Map findAllMfCartByNameWithIdAndName(String name);
	
	public List<MfCart> findAllMfCartByIds(Integer[] cids);

    public List<MfCart> findAllMfCartByUser(MfUser user);
}
