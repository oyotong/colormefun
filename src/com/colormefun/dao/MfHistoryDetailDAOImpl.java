package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfHistoryDetail;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfHistoryDetailDAOImpl extends AbstractDAO<MfHistoryDetail> implements
		BaseDAO<MfHistoryDetail>, MfHistoryDetailDAO {

	public MfHistoryDetailDAOImpl() {
		super(MfHistoryDetail.class);
	}

    public void makeCondition(MfHistoryDetail o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getOrderNo())) {
				condition.append(" and t.orderNo=? ");
				args.add(o.getOrderNo());
			}
			if (isNotNull(o.getPrice())) {
				condition.append(" and t.price=? ");
				args.add(o.getPrice());
			}
			if (isNotNull(o.getQty())) {
				condition.append(" and t.qty=? ");
				args.add(o.getQty());
			}
			if (isNotNull(o.getCaseNo())) {
				condition.append(" and t.caseNo=? ");
				args.add(o.getCaseNo());
			}
			if (isNotNull(o.getLineNo())) {
				condition.append(" and t.lineNo=? ");
				args.add(o.getLineNo());
			}
			if (isNotNull(o.getComment())) {
				condition.append(" and t.comment=? ");
				args.add(o.getComment());
			}
		
		}
	}
	
	@Override
	public List<MfHistoryDetail> findAllBySearchWithPage(MfHistoryDetail o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfHistoryDetail t where 1=1 ";
		String countQl = "select count(*) from MfHistoryDetail t where 1=1";

		makeCondition(o,condition,args);

		List<MfHistoryDetail> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.orderNo,o.orderNo from MfHistoryDetail o where o.orderNo like ?";
		String hql2 = "select count(*) from MfHistoryDetail o where o.orderNo like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfHistoryDetail> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfHistoryDetail o where o.orderNo in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfHistoryDetail> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfHistoryDetail o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfHistoryDetail t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
