package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfOrderCouponPK;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfOrderCouponPKDAOImpl extends AbstractDAO<MfOrderCouponPK> implements
		BaseDAO<MfOrderCouponPK>, MfOrderCouponPKDAO {

	public MfOrderCouponPKDAOImpl() {
		super(MfOrderCouponPK.class);
	}
	
	public void makeCondition(MfOrderCouponPK o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getOrderNo())) {
				condition.append(" and t.orderNo=? ");
				args.add(o.getOrderNo());
			}
			if (isNotNull(o.getCouponId())) {
				condition.append(" and t.couponId=? ");
				args.add(o.getCouponId());
			}
		
		}
	}
	
	@Override
	public List<MfOrderCouponPK> findAllBySearchWithPage(MfOrderCouponPK o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfOrderCouponPK t where 1=1 ";
		String countQl = "select count(*) from MfOrderCouponPK t where 1=1";

		makeCondition(o,condition,args);

		List<MfOrderCouponPK> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.orderNo,o.orderNo from MfOrderCouponPK o where o.orderNo like ?";
		String hql2 = "select count(*) from MfOrderCouponPK o where o.orderNo like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfOrderCouponPK> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfOrderCouponPK o where o.orderNo in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfOrderCouponPK> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfOrderCouponPK o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfOrderCouponPK t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
