package shop.company.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.common.util.ArraysUtils;
import shop.company.entity.SystemLoginLog;

@Repository
@AutoDAO
public class SystemLoginLogDAOImpl extends AbstractDAO<SystemLoginLog> implements
		BaseDAO<SystemLoginLog>, SystemLoginLogDAO {

	public SystemLoginLogDAOImpl() {
		super(SystemLoginLog.class);
	}

    public void makeCondition(SystemLoginLog o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getMemId())) {
				condition.append(" and t.memId=? ");
				args.add(o.getMemId());
			}
			if (isNotNull(o.getLoginId())) {
				condition.append(" and t.loginId=? ");
				args.add(o.getLoginId());
			}
			if (isNotNull(o.getLoginTime())) {
				condition.append(" and t.loginTime>=? ");
				args.add(o.getLoginTime());
			}
			if (isNotNull(o.getLoginTime2())) {
				condition.append(" and t.loginTime<=? ");
				args.add(o.getLoginTime2());
			}
			if (isNotNull(o.getLoginComment())) {
				condition.append(" and t.loginComment like ? ");
				args.add(o.getLoginComment());
			}
			if (isNotNull(o.getLoginAddr())) {
				condition.append(" and t.loginAddr=? ");
				args.add(o.getLoginAddr());
			}
			if (isNotNull(o.getLoginType())) {
				condition.append(" and t.loginType=? ");
				args.add(o.getLoginType());
			}
			if (isNotNull(o.getLoginIp())) {
				condition.append(" and t.loginIp=? ");
				args.add(o.getLoginIp());
			}
			if (isNotNull(o.getLogoutTime())) {
				condition.append(" and t.logoutTime>=? ");
				args.add(o.getLogoutTime());
			}
			if (isNotNull(o.getLogoutTime2())) {
				condition.append(" and t.logoutTime<=? ");
				args.add(o.getLogoutTime2());
			}
		
		}
	}
	
	@Override
	public void persist(SystemLoginLog transientInstance) {
		getEntityManager().persist(transientInstance);
	}
	
	@Override
	public List<SystemLoginLog> findAllBySearchWithPage(SystemLoginLog o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from SystemLoginLog t where 1=1 ";
		String countQl = "select count(*) from SystemLoginLog t where 1=1";
		String orderBy = " order by t.loginTime desc ";
		makeCondition(o,condition,args);

		List<SystemLoginLog> instance = ifindByQLWithPage(hql + condition+orderBy, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.loginId,o.loginId from SystemLoginLog o where o.loginId like ?";
		String hql2 = "select count(*) from SystemLoginLog o where o.loginId like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<SystemLoginLog> findAllByIds(java.io.Serializable[] cids){
		String hql = "from SystemLoginLog o where o.loginId in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<SystemLoginLog> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(SystemLoginLog o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from SystemLoginLog t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
