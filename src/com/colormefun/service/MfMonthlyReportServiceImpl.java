package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.dao.MfOrderDAO;
import com.colormefun.entity.MfOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfMonthlyReportDAO;
import com.colormefun.entity.MfMonthlyReport;
import shop.common.context.ApplicationContext;
import shop.common.util.page.Pagination;

@Service
@Transactional
public class MfMonthlyReportServiceImpl implements MfMonthlyReportService {

	@Autowired()
	private MfMonthlyReportDAO mfMonthlyReportDAO;
    @Autowired()
	private MfOrderDAO mfOrderDAO;

	public MfMonthlyReportDAO getMfMonthlyReportDAO() {
		return mfMonthlyReportDAO;
	}

	public void setMfMonthlyReportDAO(MfMonthlyReportDAO mfMonthlyReportDAO) {
		this.mfMonthlyReportDAO = mfMonthlyReportDAO;
	}

    public MfOrderDAO getMfOrderDAO() {
        return mfOrderDAO;
    }

    public void setMfOrderDAO(MfOrderDAO mfOrderDAO) {
        this.mfOrderDAO = mfOrderDAO;
    }

    @Override
	public void deleteMfMonthlyReport(MfMonthlyReport o) {
		mfMonthlyReportDAO.remove(o);
	}
	
	@Override
	public void deleteMfMonthlyReportByIds(String[] ids){
		mfMonthlyReportDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfMonthlyReport> findAllMfMonthlyReport() {
		return mfMonthlyReportDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfMonthlyReport> findAllMfMonthlyReportWithPage(){
		return mfMonthlyReportDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfMonthlyReport> findAllMfMonthlyReportBySearchWithPage(MfMonthlyReport o){
		return mfMonthlyReportDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfMonthlyReport getMfMonthlyReportById(Serializable id) {
		return mfMonthlyReportDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfMonthlyReportByNameWithIdAndName(String name) {
		return mfMonthlyReportDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfMonthlyReport(MfMonthlyReport o) {
		mfMonthlyReportDAO.persist(o);
	}

	@Override
	public void updateMfMonthlyReport(MfMonthlyReport o) {
		mfMonthlyReportDAO.merge(o);
	}
	
	@Override
	public List<MfMonthlyReport> findAllMfMonthlyReportByIds(String[] cids) {
		return mfMonthlyReportDAO.findAllByIds(cids);
	}

    @Override
    public List<MfOrder> findMonthlyTradingDetail(MfMonthlyReport report) {
        MfOrder order = new MfOrder();
        order.setStatus(MfOrder.OrderStatus.closed.name());

        Calendar calendar = Calendar.getInstance();
        calendar.set(new Integer(report.getYear()), new Integer(report.getMonth()) - 1, 1, 0, 0, 0);
        Date startDate = calendar.getTime();
        calendar.set(new Integer(report.getYear()), new Integer(report.getMonth()) - 1, calendar.getMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        Date endDate = calendar.getTime();

        order.setCreatedDate(startDate);
        order.setCreatedDate2(endDate);

        return mfOrderDAO.findAllBySearch(order) ;
    }

    @Override
    public MfMonthlyReport findMonthlyPerformance(MfMonthlyReport report) {

        if(report == null || report.getYear() == null || report.getMonth() == null){
            return null;
        }

        createMonthlyReport();
        List<MfMonthlyReport> list = findAllMfMonthlyReportBySearchWithPage(report);
        if(null == list || list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    public void createMonthlyReport() {
        mfMonthlyReportDAO.createMonthlyReport();
    }

    @Override
    public MfMonthlyReport findYearlyPerformance(MfMonthlyReport report) {

        if(report == null || report.getYear() == null){
            return null;
        }

        ApplicationContext context = ApplicationContext.getContext();
        Pagination pagination = new Pagination();
        pagination.setPage(1);
        pagination.setRowCount(12);
        context.setPagination(pagination);

        createMonthlyReport();
        List<MfMonthlyReport> list = findAllMfMonthlyReportBySearchWithPage(report);

        if(null == list){
            list = new ArrayList<MfMonthlyReport>();
        }

        MfMonthlyReport yearlyReport = new MfMonthlyReport();
        yearlyReport.setYear(report.getYear());

        for(MfMonthlyReport r : list){
            yearlyReport.setCaseTotal(r.getCaseTotal()+yearlyReport.getCaseTotal());
            yearlyReport.setCouponTotal(r.getCouponTotal().add(yearlyReport.getCouponTotal()));
            yearlyReport.setOrderTotal(r.getOrderTotal() + yearlyReport.getOrderTotal());
            yearlyReport.setPriceTotal(r.getPriceTotal().add(yearlyReport.getPriceTotal()));
            yearlyReport.setTicketTotal(r.getTicketTotal() + yearlyReport.getTicketTotal());

            yearlyReport.setRealCaseTotal(r.getRealCaseTotal()+yearlyReport.getRealCaseTotal());
            yearlyReport.setRealCouponTotal(r.getRealCouponTotal().add(yearlyReport.getRealCouponTotal()));
            yearlyReport.setRealOrderTotal(r.getRealOrderTotal() + yearlyReport.getRealOrderTotal());
            yearlyReport.setRealPriceTotal(r.getRealPriceTotal().add(yearlyReport.getRealPriceTotal()));
            yearlyReport.setRealTicketTotal(r.getRealTicketTotal() + yearlyReport.getRealTicketTotal());
        }

        return yearlyReport;
    }

    @Override
    public List<MfMonthlyReport> findMonthlyTradingStatistics(MfMonthlyReport report) {
        if(report == null || report.getYear() == null){
            return null;
        }

        ApplicationContext context = ApplicationContext.getContext();
        Pagination pagination = new Pagination();
        pagination.setPage(1);
        pagination.setRowCount(12);
        context.setPagination(pagination);

        createMonthlyReport();
        List<MfMonthlyReport> list = findAllMfMonthlyReportBySearchWithPage(report);

        if(null == list){
            list = new ArrayList<MfMonthlyReport>();
        }

        List<MfMonthlyReport> reports = new ArrayList<MfMonthlyReport>();

        for (int i = 0; i < 12; i ++){
            MfMonthlyReport temp = new MfMonthlyReport();
            temp.setYear(report.getYear());
            temp.setMonth(""+i);
            for(MfMonthlyReport r : list){
                if(r.getMonth().equals(""+i) || r.getMonth().equals("0"+i)){
                    temp = r;
                }
            }
            reports.add(temp);
        }

        return list;
    }

    public static void main(String[] args){
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(2014, new Integer(11) - 1, calendar.getMaximum(Calendar.DAY_OF_MONTH)-1, 0, 0, 0);
        System.out.println(calendar.getTime());
    }

}
