package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfCartCoupon;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfCartCouponDAOImpl extends AbstractDAO<MfCartCoupon> implements
		BaseDAO<MfCartCoupon>, MfCartCouponDAO {

	public MfCartCouponDAOImpl() {
		super(MfCartCoupon.class);
	}
	
	public void makeCondition(MfCartCoupon o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getCartNo())) {
				condition.append(" and t.cartNo=? ");
				args.add(o.getCartNo());
			}
			if (isNotNull(o.getCouponId())) {
				condition.append(" and t.couponId=? ");
				args.add(o.getCouponId());
			}
		
		}
	}
	
	@Override
	public List<MfCartCoupon> findAllBySearchWithPage(MfCartCoupon o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfCartCoupon t where 1=1 ";
		String countQl = "select count(*) from MfCartCoupon t where 1=1";

		makeCondition(o,condition,args);

		List<MfCartCoupon> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.couponId,o.couponId from MfCartCoupon o where o.couponId like ?";
		String hql2 = "select count(*) from MfCartCoupon o where o.couponId like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfCartCoupon> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfCartCoupon o where o.couponId in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfCartCoupon> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfCartCoupon o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfCartCoupon t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
