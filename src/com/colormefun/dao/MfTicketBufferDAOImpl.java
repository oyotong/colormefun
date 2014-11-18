package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfTicketBuffer;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfTicketBufferDAOImpl extends AbstractDAO<MfTicketBuffer> implements
		BaseDAO<MfTicketBuffer>, MfTicketBufferDAO {

	public MfTicketBufferDAOImpl() {
		super(MfTicketBuffer.class);
	}
	
	public void makeCondition(MfTicketBuffer o, StringBuffer condition,
			List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getMobilePhone())) {
				condition.append(" and t.mobilePhone=? ");
				args.add(o.getMobilePhone());
			}
			if (isNotNull(o.getEmail())) {
				condition.append(" and t.email=? ");
				args.add(o.getEmail());
			}
			if (isNotNull(o.getIndexNo())) {
				condition.append(" and t.indexNo=? ");
				args.add(o.getIndexNo());
			}
			if (isNotNull(o.getCreatedId())) {
				condition.append(" and t.createdId=? ");
				args.add(o.getCreatedId());
			}
			if (isNotNull(o.getCaseNo())) {
				condition.append(" and t.caseNo=? ");
				args.add(o.getCaseNo());
			}
			if (isNotNull(o.getCreatedDate())) {
				condition.append(" and t.createdDate=? ");
				args.add(o.getCreatedDate());
			}
		
		}
	}
	
	@Override
	public List<MfTicketBuffer> findAllBySearchWithPage(MfTicketBuffer o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfTicketBuffer t where 1=1 ";
		String countQl = "select count(*) from MfTicketBuffer t where 1=1";

		makeCondition(o,condition,args);

		List<MfTicketBuffer> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.indexNo,o.indexNo from MfTicketBuffer o where o.indexNo like ?";
		String hql2 = "select count(*) from MfTicketBuffer o where o.indexNo like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfTicketBuffer> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfTicketBuffer o where o.indexNo in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfTicketBuffer> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfTicketBuffer o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfTicketBuffer t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
