package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfHistoryDetailPK;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfHistoryDetailPKDAOImpl extends AbstractDAO<MfHistoryDetailPK> implements
		BaseDAO<MfHistoryDetailPK>, MfHistoryDetailPKDAO {

	public MfHistoryDetailPKDAOImpl() {
		super(MfHistoryDetailPK.class);
	}
	
	public void makeCondition(MfHistoryDetailPK o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getOrderNo())) {
				condition.append(" and t.orderNo=? ");
				args.add(o.getOrderNo());
			}
			if (isNotNull(o.getCaseNo())) {
				condition.append(" and t.caseNo=? ");
				args.add(o.getCaseNo());
			}
		
		}
	}
	
	@Override
	public List<MfHistoryDetailPK> findAllBySearchWithPage(MfHistoryDetailPK o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfHistoryDetailPK t where 1=1 ";
		String countQl = "select count(*) from MfHistoryDetailPK t where 1=1";

		makeCondition(o,condition,args);

		List<MfHistoryDetailPK> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.orderNo,o.orderNo from MfHistoryDetailPK o where o.orderNo like ?";
		String hql2 = "select count(*) from MfHistoryDetailPK o where o.orderNo like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfHistoryDetailPK> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfHistoryDetailPK o where o.orderNo in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfHistoryDetailPK> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfHistoryDetailPK o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfHistoryDetailPK t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
