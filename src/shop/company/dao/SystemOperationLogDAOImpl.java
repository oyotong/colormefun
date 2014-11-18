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
import shop.company.entity.SystemOperationLog;

@Repository
@AutoDAO
public class SystemOperationLogDAOImpl extends AbstractDAO<SystemOperationLog> implements
		BaseDAO<SystemOperationLog>, SystemOperationLogDAO {

	public SystemOperationLogDAOImpl() {
		super(SystemOperationLog.class);
	}
	
	public void makeCondition(SystemOperationLog o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getOpComment())) {
				condition.append(" and t.opComment like ? ");
				args.add("%"+o.getOpComment()+"%");
			}
			if (isNotNull(o.getOpId())) {
				condition.append(" and t.opId=? ");
				args.add(o.getOpId());
			}
			if (isNotNull(o.getOpType())) {
				condition.append(" and t.opType like ? ");
				args.add(o.getOpType()+"%");
			}
			if (isNotNull(o.getEndTime())) {
				condition.append(" and t.endTime>=? ");
				args.add(o.getEndTime());
			}
			if (isNotNull(o.getEndTime2())) {
				condition.append(" and t.endTime<=? ");
				args.add(o.getEndTime2());
			}
			if (isNotNull(o.getOpIp())) {
				condition.append(" and t.opIp=? ");
				args.add(o.getOpIp());
			}
			if (isNotNull(o.getStartTime())) {
				condition.append(" and t.startTime>=? ");
				args.add(o.getStartTime());
			}
			if (isNotNull(o.getStartTime2())) {
				condition.append(" and t.startTime<=? ");
				args.add(o.getStartTime2());
			}
			if (isNotNull(o.getOperation())) {
				condition.append(" and t.operation=? ");
				args.add(o.getOperation());
			}
			if (isNotNull(o.getUserId())) {
				condition.append(" and t.userId=? ");
				args.add(o.getUserId());
			}
			if (isNotNull(o.getOpAddr())) {
				condition.append(" and t.opAddr=? ");
				args.add(o.getOpAddr());
			}
			if (isNotNull(o.getLoginId())) {
				condition.append(" and t.loginId=? ");
				args.add(o.getLoginId());
			}
		
		}
	}
	
	@Override
	public List<SystemOperationLog> findAllBySearchWithPage(SystemOperationLog o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from SystemOperationLog t where 1=1 ";
		String countQl = "select count(*) from SystemOperationLog t where 1=1";
		String orderBy = " order by t.startTime desc ";
		makeCondition(o,condition,args);

		List<SystemOperationLog> instance = ifindByQLWithPage(hql + condition+orderBy, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.opId,o.opId from SystemOperationLog o where o.opId like ?";
		String hql2 = "select count(*) from SystemOperationLog o where o.opId like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<SystemOperationLog> findAllByIds(java.io.Serializable[] cids){
		String hql = "from SystemOperationLog o where o.opId in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<SystemOperationLog> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(SystemOperationLog o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from SystemOperationLog t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
