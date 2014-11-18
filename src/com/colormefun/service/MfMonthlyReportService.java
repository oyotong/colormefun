package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfMonthlyReport;
import com.colormefun.entity.MfOrder;

public interface MfMonthlyReportService {
	
	public void saveMfMonthlyReport(MfMonthlyReport o);
	
	public void updateMfMonthlyReport(MfMonthlyReport o);
	
	public void deleteMfMonthlyReport(MfMonthlyReport o);
	
	public void deleteMfMonthlyReportByIds(String[] ids);
	
	public MfMonthlyReport getMfMonthlyReportById(Serializable id);
	
	public List<MfMonthlyReport> findAllMfMonthlyReport();
	
	public List<MfMonthlyReport> findAllMfMonthlyReportWithPage();
	
	public List<MfMonthlyReport> findAllMfMonthlyReportBySearchWithPage(MfMonthlyReport o);
	
	public Map findAllMfMonthlyReportByNameWithIdAndName(String name);
	
	public List<MfMonthlyReport> findAllMfMonthlyReportByIds(String[] cids);

    public List<MfOrder> findMonthlyTradingDetail(MfMonthlyReport report);

    public MfMonthlyReport findMonthlyPerformance(MfMonthlyReport report);

    public void createMonthlyReport();

    public MfMonthlyReport findYearlyPerformance(MfMonthlyReport report);

    public List<MfMonthlyReport> findMonthlyTradingStatistics(MfMonthlyReport report);
}
