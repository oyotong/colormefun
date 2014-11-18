package com.colormefun.dao;

import java.io.Serializable;
import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfCase;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfCaseDAOImpl extends AbstractDAO<MfCase> implements
		BaseDAO<MfCase>, MfCaseDAO {

	public MfCaseDAOImpl() {
		super(MfCase.class);
	}

    public void makeCondition(MfCase o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getLocation())) {
				condition.append(" and t.locatiom like ? ");
				args.add("%"+o.getLocation()+"%");
			}
            if (isNotNull(o.getSalesVolume())) {
                condition.append(" and t.salesVolume>=? ");
                args.add(o.getSalesVolume());
            }
			if (isNotNull(o.getSalesVolume2())) {
				condition.append(" and t.salesVolume>=? ");
				args.add(o.getSalesVolume2());
			}
            if (isNotNull(o.getTicketNumber())) {
                condition.append(" and t.ticketNumber>=? ");
                args.add(o.getTicketNumber());
            }
            if (isNotNull(o.getTicketNumber2())) {
                condition.append(" and t.ticketNumber<=? ");
                args.add(o.getTicketNumber2());
            }
			if (isNotNull(o.getTimeRange())) {
				condition.append(" and t.timeRange like ? ");
				args.add("%"+o.getTimeRange()+"%");
			}
			if (isNotNull(o.getStartDate())) {
				condition.append(" and t.startDate>=? ");
				args.add(o.getStartDate());
			}
            if (isNotNull(o.getStartDate2())) {
                condition.append(" and t.startDate<=? ");
                args.add(o.getStartDate2());
            }
			if (isNotNull(o.getPicture())) {
				condition.append(" and t.picture=? ");
				args.add(o.getPicture());
			}
			if (isNotNull(o.getTitle())) {
				condition.append(" and t.title like ? ");
				args.add("%"+o.getTitle()+"%");
			}
			if (isNotNull(o.getLevel())) {
				condition.append(" and t.level=? ");
				args.add(o.getLevel());
			}
			if (isNotNull(o.getDescription())) {
				condition.append(" and t.description like ? ");
				args.add("%"+o.getDescription()+"%");
			}
			if (isNotNull(o.getRemainingTicket())) {
				condition.append(" and t.remainingTicket>=? ");
				args.add(o.getRemainingTicket());
			}
            if (isNotNull(o.getRemainingTicket2())) {
                condition.append(" and t.remainingTicket<=? ");
                args.add(o.getRemainingTicket2());
            }
			if (isNotNull(o.getActive())) {
				condition.append(" and t.active=? ");
				args.add(o.getActive());
			}
			if (isNotNull(o.getTicketPrice())) {
				condition.append(" and t.ticketPrice>=? ");
				args.add(o.getTicketPrice());
			}
            if (isNotNull(o.getTicketPrice2())) {
                condition.append(" and t.ticketPrice<=? ");
                args.add(o.getTicketPrice2());
            }
			if (isNotNull(o.getCaseNo())) {
				condition.append(" and t.caseNo=? ");
				args.add(o.getCaseNo());
			}
            if (isNotNull(o.getCreatedId())) {
                condition.append(" and t.createdId=? ");
                args.add(o.getCreatedId());
            }
			if (isNotNull(o.getCreatedDate())) {
				condition.append(" and t.createdDate>=? ");
				args.add(o.getCreatedDate());
			}
            if (isNotNull(o.getCreatedDate2())) {
                condition.append(" and t.createdDate<=? ");
                args.add(o.getCreatedDate2());
            }
		
		}
	}
	
	@Override
	public List<MfCase> findAllBySearchWithPage(MfCase o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfCase t where 1=1 ";
		String countQl = "select count(*) from MfCase t where 1=1";

		makeCondition(o,condition,args);

		List<MfCase> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.caseNo,o.title from MfCase o where o.title like ?";
		String hql2 = "select count(*) from MfCase o where o.title like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfCase> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfCase o where o.caseNo in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfCase> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfCase o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfCase t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}

    @Override
    public MfCase getActivedMfCaseById(Integer caseNo) {
        String hql = "select t from MfCase t where active='Y' and t.caseNo=?";
        return igetByQL(hql, caseNo);
    }

    @Override
    public MfCase getActivedMfCaseByIdAndStartDate(Integer caseNo, Date startDate) {
        String hql = "select t from MfCase t where active='Y' and t.start_date<? and t.caseNo=?";
        return igetByQL(hql, startDate, caseNo);
    }


}
