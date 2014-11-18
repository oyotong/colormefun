package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfContent;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfContentDAOImpl extends AbstractDAO<MfContent> implements
		BaseDAO<MfContent>, MfContentDAO {

	public MfContentDAOImpl() {
		super(MfContent.class);
	}
	
	public void makeCondition(MfContent o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getContentType())) {
				condition.append(" and t.contentType=? ");
				args.add(o.getContentType());
			}
			if (isNotNull(o.getContent())) {
				condition.append(" and t.content like ? ");
				args.add("%"+o.getContent()+"%");
			}
			if (isNotNull(o.getId())) {
				condition.append(" and t.id=? ");
				args.add(o.getId());
			}
			if (isNotNull(o.getSubTitle())) {
				condition.append(" and t.subTitle like ? ");
				args.add("%"+o.getSubTitle()+"%");
			}
			if (isNotNull(o.getTitle())) {
				condition.append(" and t.title like ? ");
				args.add("%"+o.getTitle()+"%");
			}
			if (isNotNull(o.getActive())) {
				condition.append(" and t.active=? ");
				args.add(o.getActive());
			}
			if (isNotNull(o.getCreatedId())) {
				condition.append(" and t.createdId=? ");
				args.add(o.getCreatedId());
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
		
		}
	}
	
	@Override
	public List<MfContent> findAllBySearchWithPage(MfContent o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfContent t where 1=1 ";
		String countQl = "select count(*) from MfContent t where 1=1";
        String orderBy = " order by t.contentType, t.seqNo, t.createdDate desc";

		makeCondition(o,condition,args);

		List<MfContent> instance = ifindByQLWithPage(hql + condition + orderBy, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.id,o.title from MfContent o where o.title like ?";
		String hql2 = "select count(*) from MfContent o where o.title like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfContent> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfContent o where o.id in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfContent> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfContent o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfContent t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
