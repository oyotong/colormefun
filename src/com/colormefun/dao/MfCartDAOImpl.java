package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfCart;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfCartDAOImpl extends AbstractDAO<MfCart> implements
		BaseDAO<MfCart>, MfCartDAO {

	public MfCartDAOImpl() {
		super(MfCart.class);
	}

    public void makeCondition(MfCart o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getUser()) && isNotNull(o.getUser().getUserNo())) {
				condition.append(" and t.user.userNo=? ");
				args.add(o.getUser().getUserNo());
			}
			if (isNotNull(o.getCartNo())) {
				condition.append(" and t.cartNo=? ");
				args.add(o.getCartNo());
			}
            if (isNotNull(o.getQty())) {
                condition.append(" and t.qty=? ");
                args.add(o.getQty());
            }
            if (isNotNull(o.getMfCase()) && isNotNull(o.getMfCase().getCaseNo())) {
                condition.append(" and t.mfCase.caseNo=? ");
                args.add(o.getMfCase().getCaseNo());
            }
			if (isNotNull(o.getActive())) {
				condition.append(" and t.active=? ");
				args.add(o.getActive());
			}
			if (isNotNull(o.getCreatedDate())) {
				condition.append(" and t.createdDate=? ");
				args.add(o.getCreatedDate());
			}
			if (isNotNull(o.getOrderedDate())) {
				condition.append(" and t.orderedDate=? ");
				args.add(o.getOrderedDate());
			}
		
		}
	}
	
	@Override
	public List<MfCart> findAllBySearchWithPage(MfCart o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfCart t where 1=1 ";
		String countQl = "select count(*) from MfCart t where 1=1";

		makeCondition(o,condition,args);

		List<MfCart> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.cartNo,o.cartNo from MfCart o where o.cartNo like ?";
		String hql2 = "select count(*) from MfCart o where o.cartNo like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfCart> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfCart o where o.cartNo in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfCart> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfCart o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfCart t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
