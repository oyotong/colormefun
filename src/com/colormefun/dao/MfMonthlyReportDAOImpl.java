package com.colormefun.dao;

import java.sql.CallableStatement;
import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfMonthlyReport;
import shop.common.util.ArraysUtils;

import javax.persistence.Query;

@Repository
@AutoDAO
public class MfMonthlyReportDAOImpl extends AbstractDAO<MfMonthlyReport> implements
		BaseDAO<MfMonthlyReport>, MfMonthlyReportDAO {

	public MfMonthlyReportDAOImpl() {
		super(MfMonthlyReport.class);
	}

    public void makeCondition(MfMonthlyReport o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getRealCaseTotal())) {
				condition.append(" and t.realCaseTotal=? ");
				args.add(o.getRealCaseTotal());
			}
			if (isNotNull(o.getRealPriceTotal())) {
				condition.append(" and t.realPriceTotal=? ");
				args.add(o.getRealPriceTotal());
			}
			if (isNotNull(o.getOrderTotal())) {
				condition.append(" and t.orderTotal=? ");
				args.add(o.getOrderTotal());
			}
			if (isNotNull(o.getCaseTotal())) {
				condition.append(" and t.caseTotal=? ");
				args.add(o.getCaseTotal());
			}
			if (isNotNull(o.getStatus())) {
				condition.append(" and t.status=? ");
				args.add(o.getStatus());
			}
			if (isNotNull(o.getPriceTotal())) {
				condition.append(" and t.priceTotal=? ");
				args.add(o.getPriceTotal());
			}
			if (isNotNull(o.getRealOrderTotal())) {
				condition.append(" and t.realOrderTotal=? ");
				args.add(o.getRealOrderTotal());
			}
			if (isNotNull(o.getCouponTotal())) {
				condition.append(" and t.couponTotal=? ");
				args.add(o.getCouponTotal());
			}
			if (isNotNull(o.getTicketTotal())) {
				condition.append(" and t.ticketTotal=? ");
				args.add(o.getTicketTotal());
			}
			if (isNotNull(o.getRealTicketTotal())) {
				condition.append(" and t.realTicketTotal=? ");
				args.add(o.getRealTicketTotal());
			}
			if (isNotNull(o.getMonth())) {
				condition.append(" and t.month=? ");
				args.add(o.getMonth());
			}
			if (isNotNull(o.getCreatedId())) {
				condition.append(" and t.createdId=? ");
				args.add(o.getCreatedId());
			}
			if (isNotNull(o.getYear())) {
				condition.append(" and t.year=? ");
				args.add(o.getYear());
			}
			if (isNotNull(o.getComment())) {
				condition.append(" and t.comment=? ");
				args.add(o.getComment());
			}
			if (isNotNull(o.getCreatedDate())) {
				condition.append(" and t.createdDate=? ");
				args.add(o.getCreatedDate());
			}
			if (isNotNull(o.getRealCouponTotal())) {
				condition.append(" and t.realCouponTotal=? ");
				args.add(o.getRealCouponTotal());
			}
		
		}
	}
	
	@Override
	public List<MfMonthlyReport> findAllBySearchWithPage(MfMonthlyReport o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfMonthlyReport t where 1=1 ";
		String countQl = "select count(*) from MfMonthlyReport t where 1=1";

		makeCondition(o,condition,args);

		List<MfMonthlyReport> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.year,o.year from MfMonthlyReport o where o.year like ?";
		String hql2 = "select count(*) from MfMonthlyReport o where o.year like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfMonthlyReport> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfMonthlyReport o where o.year in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfMonthlyReport> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfMonthlyReport o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfMonthlyReport t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}

    @Override
    public void createMonthlyReport() {
        Query query = getEntityManager().createNativeQuery("{call monthly_report()}");
        query.executeUpdate();
    }
}
