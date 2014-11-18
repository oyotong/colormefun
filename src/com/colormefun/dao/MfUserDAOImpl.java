package com.colormefun.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfUser;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfUserDAOImpl extends AbstractDAO<MfUser> implements
		BaseDAO<MfUser>, MfUserDAO {

	public MfUserDAOImpl() {
		super(MfUser.class);
	}
	/*
	private Date birthday;
    private String sex;
    private String favorite;
    private String myColor;
	 */
	public void makeCondition(MfUser o, StringBuffer condition,
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
            if (isNotNull(o.getUserName())) {
                condition.append(" and t.userName=? ");
                args.add(o.getUserName());
            }
			if (isNotNull(o.getUserNo())) {
				condition.append(" and t.userNo=? ");
				args.add(o.getUserNo());
			}
			if (isNotNull(o.getRegisteredDate())) {
				condition.append(" and t.registeredDate>=? ");
				args.add(o.getRegisteredDate());
			}
            if (isNotNull(o.getRegisteredDate2())) {
                condition.append(" and t.registeredDate<=? ");
                args.add(o.getRegisteredDate2());
            }

            if (isNotNull(o.getBirthday())) {
                condition.append(" and t.birthday>=? ");
                args.add(o.getBirthday());
            }
            if (isNotNull(o.getBirthday2())) {
                condition.append(" and t.birthday<=? ");
                args.add(o.getBirthday2());
            }
			if (isNotNull(o.getActive())) {
				condition.append(" and t.active=? ");
				args.add(o.getActive());
			}
			if (isNotNull(o.getPassword())) {
				condition.append(" and t.password=? ");
				args.add(o.getPassword());
			}
            if (isNotNull(o.getSex())) {
                condition.append(" and t.sex=? ");
                args.add(o.getSex());
            }
            if (isNotNull(o.getFavorite())) {
                condition.append(" and t.favorite like ? ");
                args.add("%"+o.getFavorite()+"%");
            }
            if (isNotNull(o.getMyColor())) {
                condition.append(" and t.myColor like ? ");
                args.add("%"+o.getMyColor()+"%");
            }
			if (isNotNull(o.getLetMeKnow())) {
				condition.append(" and t.letMeKnow=? ");
				args.add(o.getLetMeKnow());
			}
		
		}
	}
	
	@Override
	public List<MfUser> findAllBySearchWithPage(MfUser o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from MfUser t where 1=1 ";
		String countQl = "select count(*) from MfUser t where 1=1";

		makeCondition(o,condition,args);

		List<MfUser> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.userNo,o.userNo from MfUser o where o.userNo like ?";
		String hql2 = "select count(*) from MfUser o where o.userNo like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<MfUser> findAllByIds(java.io.Serializable[] cids){
		String hql = "from MfUser o where o.userNo in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<MfUser> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(MfUser o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from MfUser t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
