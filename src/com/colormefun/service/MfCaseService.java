package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfCase;

public interface MfCaseService {
	
	public void saveMfCase(MfCase o);
	
	public void updateMfCase(MfCase o);
	
	public void deleteMfCase(MfCase o);
	
	public void deleteMfCaseByIds(Integer[] ids);
	
	public MfCase getMfCaseById(Serializable id);
	
	public List<MfCase> findAllMfCase();
	
	public List<MfCase> findAllMfCaseWithPage();
	
	public List<MfCase> findAllMfCaseBySearchWithPage(MfCase o);
	
	public Map findAllMfCaseByNameWithIdAndName(String name);
	
	public List<MfCase> findAllMfCaseByIds(Integer[] cids);

    public List<MfCase> findCaseByCurrentMonth();

    public Integer getCurrentMonth();

    public List<MfCase> findCaseByNextMonth();

    public List<MfCase> findCaseByPreviousMonth();

    public Calendar getNextMonthCalendar();

    public Integer getNextMonth();

    public Calendar getPreviousMonthCalendar();

    public Integer getPreviousMonth();

    public Calendar getFirstDate(Calendar calendar);

    public Calendar getEndDate(Calendar calendar);

    public Integer getFirstDayOfWeek(Calendar calendar);

    public Integer getEndDateDayOfWeek(Calendar calendar);

    public MfCase getActivedMfCaseById(String caseNo);

    public List<MfCase> findCaseByCurrentMonthByStatus(String status);

    public Integer getPreviousMonthByStatus(String status);

    public Integer getNextMonthByStatus(String status);

    public Date getCurrentDateByStatus(String status);

    public Integer getFirstDayByStatus(String status);

    public Integer getMaxDayOfMonthByStatus(String status);

    public Calendar getFirstDateByStatus(String status);

    public Calendar addDate(Calendar calendar, Integer day);
}
