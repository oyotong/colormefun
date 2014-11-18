package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfCoupon;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfCouponDAOImpl extends AbstractDAO<MfCoupon> implements
		BaseDAO<MfCoupon>, MfCouponDAO {

	public MfCouponDAOImpl() {
		super(MfCoupon.class);
	}
	
	public void makeCondition(MfCoupon o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getCouponNo())) {
				condition.append(" and t.couponNo=? ");
				args.add(o.getCouponNo());
			}
			if (isNotNull(o.getCouponType())) {
				condition.append(" and t.couponType=? ");
				args.add(o.getCouponType());
			}
			if (isNotNull(o.getStatus())) {
				condition.append(" and t.status=? ");
				args.add(o.getStatus());
			}
			if (isNotNull(o.getCouponId())) {
				condition.append(" and t.couponId=? ");
				args.add(o.getCouponId());
			}
			if (isNotNull(o.getPassword())) {
				condition.append(" and t.password=? ");
				args.add(o.getPassword());
			}
			if (isNotNull(o.getDiscount())) {
				condition.append(" and t.discount>=? ");
				args.add(o.getDiscount());
			}
            if (isNotNull(o.getDiscount2())) {
                condition.append(" and t.discount<=? ");
                args.add(o.getDiscount2());
            }
			if (isNotNull(o.getUserNo())) {
				condition.append(" and t.userNo=? ");
				args.add(o.getUserNo());
			}
			if (isNotNull(o.getDescription())) {
				condition.append(" and t.description=? ");
				args.add(o.getDescription());
			}
			if (isNotNull(o.getCreatedId())) {
				condition.append(" and t.createdId=? ");
				args.add(o.getCreatedId());
			}
			if (isNotNull(o.getActive())) {
				condition.append(" and t.active=? ");
				args.add(o.getActive());
			}
			if (isNotNull(o.getDeduction())) {
				condition.append(" and t.deduction>=? ");
				args.add(o.getDeduction());
			}
            if (isNotNull(o.getDeduction2())) {
                condition.append(" and t.deduction<=? ");
                args.add(o.getDeduction2());
            }
			if (isNotNull(o.getDeadline())) {
				condition.append(" and t.deadline>=? ");
				args.add(o.getDeadline());
			}
            if (isNotNull(o.getDeadline2())) {
                condition.append(" and t.deadline<=? ");
                args.add(o.getDeadline2());
            }
			if (isNotNull(o.getCreatedDate())) {
				condition.append(" and t.createdDate>=? ");
				args.add(o.getCreatedDate());
			}
            if (isNotNull(o.getCreatedDate2())) {
                condition.append(" and t.createdDate<=? ");
                args.add(o.getCreatedDate2());
            }

            if (isNotNull(o.getTotal())) {
                condition.append(" and t.total=? ");
                args.add(o.getTotal());
            }

            if (isNotNull(o.getVersion())) {
                condition.append(" and t.version=? ");
                args.add(o.getVersion());
            }
		
		}
	}
	
	@Override
	public List<MfCoupon> findAllBySearchWithPage(MfCoupon o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfCoupon t where 1=1 ";
		String countQl = "select count(*) from MfCoupon t where 1=1";

		makeCondition(o,condition,args);

		List<MfCoupon> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.couponId,o.couponId from MfCoupon o where o.couponId like ?";
		String hql2 = "select count(*) from MfCoupon o where o.couponId like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfCoupon> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfCoupon o where o.couponId in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfCoupon> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfCoupon o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfCoupon t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
