package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfOrder;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfOrderDAOImpl extends AbstractDAO<MfOrder> implements
		BaseDAO<MfOrder>, MfOrderDAO {

	public MfOrderDAOImpl() {
		super(MfOrder.class);
	}
	
	public void makeCondition(MfOrder o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getOrderNo())) {
				condition.append(" and t.orderNo like ? ");
				args.add("%"+o.getOrderNo()+"%");
			}
			if (isNotNull(o.getStatus())) {
				condition.append(" and t.status=? ");
				args.add(o.getStatus());
			}
//			if (isNotNull(o.getUserNo())) {
//				condition.append(" and t.userNo=? ");
//				args.add(o.getUserNo());
//			}
			if (isNotNull(o.getActive())) {
				condition.append(" and t.active=? ");
				args.add(o.getActive());
			}
			if (isNotNull(o.getComment())) {
				condition.append(" and t.comment like ? ");
				args.add("%"+o.getComment()+"%");
			}
			if (isNotNull(o.getCreatedDate())) {
				condition.append(" and t.createdDate>=? ");
				args.add(o.getCreatedDate());
			}
            if (isNotNull(o.getCreatedDate2())) {
                condition.append(" and t.createdDate<=? ");
                args.add(o.getCreatedDate2());
            }
            if (isNotNull(o.getUserName())) {
                condition.append(" and (t.user.userName like ? or t.user.mobilePhone like ?)");
                args.add("%"+o.getUserName()+"%");
                args.add("%"+o.getUserName()+"%");
            }
            if (isNotNull(o.getSummary())) {
                condition.append(" and (select sum(d.price * d.qty) from t.details d)>=? ");
                args.add(o.getSummary());
            }
            if (isNotNull(o.getSummary2())) {
                condition.append(" and (select sum(d.price * d.qty) from t.details d)<=? ");
                args.add(o.getSummary2());
            }
            if (isNotNull(o.getCouponSummary())) {
                condition.append(" and (select sum(c.deduction) from t.coupons c)>=? ");
                args.add(o.getCouponSummary());
            }
            if (isNotNull(o.getCouponSummary2())) {
                condition.append(" and (select sum(c.deduction) from t.coupons c)<=? ");
                args.add(o.getCouponSummary2());
            }
		}
	}
	
	@Override
	public List<MfOrder> findAllBySearchWithPage(MfOrder o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfOrder t where 1=1 ";
		String countQl = "select count(*) from MfOrder t where 1=1";

		makeCondition(o,condition,args);

		List<MfOrder> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}

    @Override
    public List<MfOrder> findAllBySearch(MfOrder o) {

        List args = new ArrayList();
        StringBuffer condition = new StringBuffer(500);

        String hql = "select t from MfOrder t where 1=1 ";

        makeCondition(o,condition,args);

        List<MfOrder> instance = ifindByQL(hql + condition, args.toArray(new Object[0]));

        return instance;
    }
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.orderNo,o.orderNo from MfOrder o where o.orderNo like ?";
		String hql2 = "select count(*) from MfOrder o where o.orderNo like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfOrder> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfOrder o where o.orderNo in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfOrder> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfOrder o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfOrder t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
