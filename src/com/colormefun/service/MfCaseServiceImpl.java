package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfCaseDAO;
import com.colormefun.entity.MfCase;
import shop.common.util.DateUtils;

@Service
@Transactional
public class MfCaseServiceImpl implements MfCaseService {

	@Autowired()
	private MfCaseDAO mfCaseDAO;

	public MfCaseDAO getMfCaseDAO() {
		return mfCaseDAO;
	}

	public void setMfCaseDAO(MfCaseDAO mfCaseDAO) {
		this.mfCaseDAO = mfCaseDAO;
	}

	@Override
	public void deleteMfCase(MfCase o) {
		mfCaseDAO.remove(o);
	}
	
	@Override
	public void deleteMfCaseByIds(Integer[] ids){
		mfCaseDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfCase> findAllMfCase() {
		return mfCaseDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCase> findAllMfCaseWithPage(){
		return mfCaseDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCase> findAllMfCaseBySearchWithPage(MfCase o){
		return mfCaseDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfCase getMfCaseById(Serializable id) {
		return mfCaseDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfCaseByNameWithIdAndName(String name) {
		return mfCaseDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfCase(MfCase o) {
		mfCaseDAO.persist(o);
	}

	@Override
	public void updateMfCase(MfCase o) {
		mfCaseDAO.merge(o);
	}
	
	@Override
	public List<MfCase> findAllMfCaseByIds(Integer[] cids) {
		return mfCaseDAO.findAllByIds(cids);
	}

    @Override
    @Transactional(readOnly = true)
    public MfCase getActivedMfCaseById(String caseNo) {
        if(null == caseNo || caseNo.trim().length() == 0){
            return null;
        }
        return mfCaseDAO.getActivedMfCaseById(new Integer(caseNo));
    }

    @Override
    public List<MfCase> findCaseByCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return findCaseByCalender(calendar);
    }

    @Override
    public List<MfCase> findCaseByNextMonth() {
        Calendar calendar = getNextMonthCalendar();
        return findCaseByCalender(calendar);
    }

    @Override
    public List<MfCase> findCaseByPreviousMonth() {
        Calendar calendar = getPreviousMonthCalendar();
        return findCaseByCalender(calendar);
    }

    private List<MfCase> findCaseByCalender(Calendar calendar) {

        MfCase mfCase = new MfCase();
        mfCase.setActive("Y");
        mfCase.setStartDate(getFirstDate(calendar).getTime());
        mfCase.setStartDate2(getEndDate(calendar).getTime());

        return findAllMfCaseBySearchWithPage(mfCase);
    }



    @Override
    public Integer getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    @Override
    public Calendar getNextMonthCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,1);
        return calendar;
    }

    @Override
    public Integer getNextMonth() {
        return getNextMonthCalendar().get(Calendar.MONTH) + 1;
    }

    @Override
    public Calendar getPreviousMonthCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar;
    }

    @Override
    public Integer getPreviousMonth() {
        return getPreviousMonthCalendar().get(Calendar.MONTH) + 1;
    }

    @Override
    public Calendar getFirstDate(Calendar calendar) {

        int first = calendar.getMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, first);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;

    }

    @Override
    public Calendar getEndDate(Calendar calendar) {

        int end = calendar.getMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, end);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);

        return calendar;
    }

    @Override
    public Integer getFirstDayOfWeek(Calendar calendar) {
        return getFirstDate(calendar).get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public Integer getEndDateDayOfWeek(Calendar calendar) {
        return getEndDate(calendar).get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public List<MfCase> findCaseByCurrentMonthByStatus(String status) {
        if(null == status){
            return findCaseByCurrentMonth();
        }
        if("next".equalsIgnoreCase(status)){
            return findCaseByNextMonth();
        }
        if("previous".equalsIgnoreCase(status)){
            return findCaseByPreviousMonth();
        }
        return null;
    }

    @Override
    public Integer getPreviousMonthByStatus(String status) {
        if(null == status){
            return getPreviousMonth();
        }
        if("next".equalsIgnoreCase(status)){
            return getCurrentMonth();
        }
        if("previous".equalsIgnoreCase(status)){
            return null;
        }
        return null;
    }

    @Override
    public Integer getNextMonthByStatus(String status) {
        if(null == status){
            return getNextMonth();
        }
        if("next".equalsIgnoreCase(status)){
            return null;
        }
        if("previous".equalsIgnoreCase(status)){
            return getCurrentMonth();
        }
        return null;
    }

    @Override
    public Date getCurrentDateByStatus(String status) {
        if(null == status){
            return DateUtils.now();
        }
        if("next".equalsIgnoreCase(status)){
            return getNextMonthCalendar().getTime();
        }
        if("previous".equalsIgnoreCase(status)){
            return getPreviousMonthCalendar().getTime();
        }
        return null;
    }


    @Override
    public Calendar getFirstDateByStatus(String status) {
        Calendar calendar = Calendar.getInstance();
        if("next".equalsIgnoreCase(status)){
            calendar = getNextMonthCalendar();
        }
        if("previous".equalsIgnoreCase(status)){
            calendar = getPreviousMonthCalendar();
        }
        if(calendar == null) return null;
        return getFirstDate(calendar);
    }

    @Override
    public Calendar addDate(Calendar calendar, Integer day) {
        Calendar clone = (Calendar) calendar.clone();
        clone.add(Calendar.DATE, day);
        return clone;
    }


    @Override
    public Integer getFirstDayByStatus(String status) {
        Calendar calendar = Calendar.getInstance();
        if("next".equalsIgnoreCase(status)){
            calendar = getNextMonthCalendar();
        }
        if("previous".equalsIgnoreCase(status)){
            calendar = getPreviousMonthCalendar();
        }
        if(calendar == null) return null;
        return getFirstDate(calendar).get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public Integer getMaxDayOfMonthByStatus(String status) {
        Calendar calendar = Calendar.getInstance();
        if("next".equalsIgnoreCase(status)){
            calendar = getNextMonthCalendar();
        }
        if("previous".equalsIgnoreCase(status)){
            calendar = getPreviousMonthCalendar();
        }
        if(calendar == null) return null;
        return calendar.getMaximum(Calendar.DAY_OF_MONTH);
    }


}
