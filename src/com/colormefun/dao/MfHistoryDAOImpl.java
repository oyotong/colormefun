package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfHistory;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfHistoryDAOImpl extends AbstractDAO<MfHistory> implements
		BaseDAO<MfHistory>, MfHistoryDAO {

	public MfHistoryDAOImpl() {
		super(MfHistory.class);
	}

    public void makeCondition(MfHistory o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getOrderNo())) {
				condition.append(" and t.orderNo=? ");
				args.add(o.getOrderNo());
			}
			if (isNotNull(o.getStatus())) {
				condition.append(" and t.status=? ");
				args.add(o.getStatus());
			}
			if (isNotNull(o.getUserNo())) {
				condition.append(" and t.userNo=? ");
				args.add(o.getUserNo());
			}
			if (isNotNull(o.getActive())) {
				condition.append(" and t.active=? ");
				args.add(o.getActive());
			}
			if (isNotNull(o.getComment())) {
				condition.append(" and t.comment=? ");
				args.add(o.getComment());
			}
			if (isNotNull(o.getCreatedDate())) {
				condition.append(" and t.createdDate=? ");
				args.add(o.getCreatedDate());
			}
		
		}
	}
	
	@Override
	public List<MfHistory> findAllBySearchWithPage(MfHistory o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfHistory t where 1=1 ";
		String countQl = "select count(*) from MfHistory t where 1=1";

		makeCondition(o,condition,args);

		List<MfHistory> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.orderNo,o.orderNo from MfHistory o where o.orderNo like ?";
		String hql2 = "select count(*) from MfHistory o where o.orderNo like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfHistory> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfHistory o where o.orderNo in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfHistory> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfHistory o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfHistory t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
