package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfMonthlyReportPK;

public interface MfMonthlyReportPKService {
	
	public void saveMfMonthlyReportPK(MfMonthlyReportPK o);
	
	public void updateMfMonthlyReportPK(MfMonthlyReportPK o);
	
	public void deleteMfMonthlyReportPK(MfMonthlyReportPK o);
	
	public void deleteMfMonthlyReportPKByIds(String[] ids);
	
	public MfMonthlyReportPK getMfMonthlyReportPKById(Serializable id);
	
	public List<MfMonthlyReportPK> findAllMfMonthlyReportPK();
	
	public List<MfMonthlyReportPK> findAllMfMonthlyReportPKWithPage();
	
	public List<MfMonthlyReportPK> findAllMfMonthlyReportPKBySearchWithPage(MfMonthlyReportPK o);
	
	public Map findAllMfMonthlyReportPKByNameWithIdAndName(String name);
	
	public List<MfMonthlyReportPK> findAllMfMonthlyReportPKByIds(String[] cids);
	
}
