package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfFavorite;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfFavoriteDAOImpl extends AbstractDAO<MfFavorite> implements
		BaseDAO<MfFavorite>, MfFavoriteDAO {

	public MfFavoriteDAOImpl() {
		super(MfFavorite.class);
	}
	
	public void makeCondition(MfFavorite o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getFavoriteId())) {
				condition.append(" and t.favoriteId=? ");
				args.add(o.getFavoriteId());
			}
			if (isNotNull(o.getDeletedDate())) {
				condition.append(" and t.deletedDate=? ");
				args.add(o.getDeletedDate());
			}
			if (isNotNull(o.getUser()) && isNotNull(o.getUser().getUserNo())) {
				condition.append(" and t.user.userNo=? ");
				args.add(o.getUser().getUserNo());
			}
			if (isNotNull(o.getActive())) {
				condition.append(" and t.active=? ");
				args.add(o.getActive());
			}
			if (isNotNull(o.getMfCase()) && isNotNull(o.getMfCase().getCaseNo())) {
				condition.append(" and t.mfCase.caseNo=? ");
				args.add(o.getMfCase().getCaseNo());
			}
			if (isNotNull(o.getComment())) {
				condition.append(" and t.comment=? ");
				args.add(o.getComment());
			}
			if (isNotNull(o.getCreatedDate())) {
				condition.append(" and t.createdDate>=? ");
				args.add(o.getCreatedDate());
			}

            if (isNotNull(o.getCreatedDate2())) {
                condition.append(" and t.createdDate<=? ");
                args.add(o.getCreatedDate());
            }
		
		}
	}
	
	@Override
	public List<MfFavorite> findAllBySearchWithPage(MfFavorite o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfFavorite t where 1=1 ";
		String countQl = "select count(*) from MfFavorite t where 1=1";

		makeCondition(o,condition,args);

		List<MfFavorite> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.favoriteId,o.favoriteId from MfFavorite o where o.favoriteId like ?";
		String hql2 = "select count(*) from MfFavorite o where o.favoriteId like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfFavorite> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfFavorite o where o.favoriteId in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfFavorite> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfFavorite o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfFavorite t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
