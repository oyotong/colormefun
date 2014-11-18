package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfMonthlyReportPK;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfMonthlyReportPKDAOImpl extends AbstractDAO<MfMonthlyReportPK> implements
		BaseDAO<MfMonthlyReportPK>, MfMonthlyReportPKDAO {

	public MfMonthlyReportPKDAOImpl() {
		super(MfMonthlyReportPK.class);
	}

    public void makeCondition(MfMonthlyReportPK o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getMonth())) {
				condition.append(" and t.month=? ");
				args.add(o.getMonth());
			}
			if (isNotNull(o.getYear())) {
				condition.append(" and t.year=? ");
				args.add(o.getYear());
			}
		
		}
	}
	
	@Override
	public List<MfMonthlyReportPK> findAllBySearchWithPage(MfMonthlyReportPK o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfMonthlyReportPK t where 1=1 ";
		String countQl = "select count(*) from MfMonthlyReportPK t where 1=1";

		makeCondition(o,condition,args);

		List<MfMonthlyReportPK> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.year,o.year from MfMonthlyReportPK o where o.year like ?";
		String hql2 = "select count(*) from MfMonthlyReportPK o where o.year like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfMonthlyReportPK> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfMonthlyReportPK o where o.year in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfMonthlyReportPK> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfMonthlyReportPK o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfMonthlyReportPK t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
