package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfVipCase;

public interface MfVipCaseService {
	
	public void saveMfVipCase(MfVipCase o);
	
	public void updateMfVipCase(MfVipCase o);
	
	public void deleteMfVipCase(MfVipCase o);
	
	public void deleteMfVipCaseByIds(Integer[] ids);
	
	public MfVipCase getMfVipCaseById(Serializable id);
	
	public List<MfVipCase> findAllMfVipCase();
	
	public List<MfVipCase> findAllMfVipCaseWithPage();
	
	public List<MfVipCase> findAllMfVipCaseBySearchWithPage(MfVipCase o);
	
	public Map findAllMfVipCaseByNameWithIdAndName(String name);
	
	public List<MfVipCase> findAllMfVipCaseByIds(Integer[] cids);
	
}
