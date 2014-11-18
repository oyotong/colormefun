package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfCartDetailPK;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfCartDetailPKDAOImpl extends AbstractDAO<MfCartDetailPK> implements
		BaseDAO<MfCartDetailPK>, MfCartDetailPKDAO {

	public MfCartDetailPKDAOImpl() {
		super(MfCartDetailPK.class);
	}
	
	public void makeCondition(MfCartDetailPK o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getCartNo())) {
				condition.append(" and t.cartNo=? ");
				args.add(o.getCartNo());
			}
			if (isNotNull(o.getCaseNo())) {
				condition.append(" and t.caseNo=? ");
				args.add(o.getCaseNo());
			}
		
		}
	}
	
	@Override
	public List<MfCartDetailPK> findAllBySearchWithPage(MfCartDetailPK o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfCartDetailPK t where 1=1 ";
		String countQl = "select count(*) from MfCartDetailPK t where 1=1";

		makeCondition(o,condition,args);

		List<MfCartDetailPK> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.caseNo,o.caseNo from MfCartDetailPK o where o.caseNo like ?";
		String hql2 = "select count(*) from MfCartDetailPK o where o.caseNo like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfCartDetailPK> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfCartDetailPK o where o.caseNo in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfCartDetailPK> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfCartDetailPK o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfCartDetailPK t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
