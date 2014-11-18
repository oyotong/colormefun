package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfOrderDetailPK;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfOrderDetailPKDAOImpl extends AbstractDAO<MfOrderDetailPK> implements
		BaseDAO<MfOrderDetailPK>, MfOrderDetailPKDAO {

	public MfOrderDetailPKDAOImpl() {
		super(MfOrderDetailPK.class);
	}

    public void makeCondition(MfOrderDetailPK o, StringBuffer condition,
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
	public List<MfOrderDetailPK> findAllBySearchWithPage(MfOrderDetailPK o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfOrderDetailPK t where 1=1 ";
		String countQl = "select count(*) from MfOrderDetailPK t where 1=1";

		makeCondition(o,condition,args);

		List<MfOrderDetailPK> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.orderNo,o.orderNo from MfOrderDetailPK o where o.orderNo like ?";
		String hql2 = "select count(*) from MfOrderDetailPK o where o.orderNo like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfOrderDetailPK> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfOrderDetailPK o where o.orderNo in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfOrderDetailPK> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfOrderDetailPK o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfOrderDetailPK t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
