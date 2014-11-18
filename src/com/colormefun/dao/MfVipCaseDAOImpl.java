package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfVipCase;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfVipCaseDAOImpl extends AbstractDAO<MfVipCase> implements
		BaseDAO<MfVipCase>, MfVipCaseDAO {

	public MfVipCaseDAOImpl() {
		super(MfVipCase.class);
	}
	
	public void makeCondition(MfVipCase o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getRegion())) {
				condition.append(" and t.region=? ");
				args.add(o.getRegion());
			}
			if (isNotNull(o.getCaseType())) {
				condition.append(" and t.caseType=? ");
				args.add(o.getCaseType());
			}
			if (isNotNull(o.getContactPhone())) {
				condition.append(" and t.contactPhone like ? ");
				args.add("%"+o.getContactPhone()+"%");
			}
			if (isNotNull(o.getStatus())) {
				condition.append(" and t.status=? ");
				args.add(o.getStatus());
			}
			if (isNotNull(o.getLocation())) {
				condition.append(" and t.location like ? ");
				args.add("%"+o.getLocation()+"%");
			}
			if (isNotNull(o.getEmail())) {
				condition.append(" and t.email like ? ");
				args.add("%"+o.getEmail()+"%");
			}
			if (isNotNull(o.getKidsNum())) {
				condition.append(" and t.kidsNum>=? ");
				args.add(o.getKidsNum());
			}
            if (isNotNull(o.getKidsNum2())) {
                condition.append(" and t.kidsNum<=? ");
                args.add(o.getKidsNum2());
            }
			if (isNotNull(o.getNeedLocation())) {
				condition.append(" and t.needLocation=? ");
				args.add(o.getNeedLocation());
			}
			if (isNotNull(o.getCompanyName())) {
				condition.append(" and t.companyName like ? ");
				args.add("%"+o.getCompanyName()+"%");
			}
			if (isNotNull(o.getMemberNum())) {
				condition.append(" and t.memberNum>=? ");
				args.add(o.getMemberNum());
			}
            if (isNotNull(o.getMemberNum2())) {
                condition.append(" and t.memberNum<=? ");
                args.add(o.getMemberNum2());
            }
			if (isNotNull(o.getArea())) {
				condition.append(" and t.area like ? ");
				args.add("%"+o.getArea()+"%");
			}
			if (isNotNull(o.getDescription())) {
				condition.append(" and t.description like ? ");
				args.add("%"+o.getDescription()+"%");
			}
			if (isNotNull(o.getContactName())) {
				condition.append(" and t.contactName like ? ");
				args.add("%"+o.getContactName()+"%");
			}
			if (isNotNull(o.getActive())) {
				condition.append(" and t.active=? ");
				args.add(o.getActive());
			}
			if (isNotNull(o.getCaseNo())) {
				condition.append(" and t.caseNo=? ");
				args.add(o.getCaseNo());
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
	public List<MfVipCase> findAllBySearchWithPage(MfVipCase o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfVipCase t where 1=1 ";
		String countQl = "select count(*) from MfVipCase t where 1=1";

		makeCondition(o,condition,args);

		List<MfVipCase> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.caseNo,o.companyName from MfVipCase o where o.companyName like ?";
		String hql2 = "select count(*) from MfVipCase o where o.companyName like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfVipCase> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfVipCase o where o.caseNo in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfVipCase> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfVipCase o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfVipCase t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
