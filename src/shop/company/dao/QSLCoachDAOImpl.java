package shop.company.dao;

import java.util.*;

import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLCoach;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class QSLCoachDAOImpl extends AbstractDAO<QSLCoach> implements
		BaseDAO<QSLCoach>, QSLCoachDAO {

	public QSLCoachDAOImpl() {
		super(QSLCoach.class);
	}
	
	public void makeCondition(QSLCoach o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getLastName())) {
				condition.append(" and t.lastName like ? ");
				args.add(o.getLastName());
			}
			if (isNotNull(o.getEnrollDate())) {
				condition.append(" and t.enrollDate >= ? ");
				args.add(o.getEnrollDate());
			}
			if (isNotNull(o.getEnrollDate2())) {
				condition.append(" and t.enrollDate <= ? ");
				args.add(o.getEnrollDate2());
			}
			if (isNotNull(o.getTermDate())) {
				condition.append(" and t.termDate>=? ");
				args.add(o.getTermDate());
			}
			if (isNotNull(o.getTermDate2())) {
				condition.append(" and t.termDate<=? ");
				args.add(o.getTermDate2());
			}
			if (isNotNull(o.getCompanyNo())) {
				condition.append(" and t.companyNo=? ");
				args.add(o.getCompanyNo());
			}
			if (isNotNull(o.getFirstName())) {
				condition.append(" and t.firstName like ? ");
				args.add(o.getFirstName());
			}
			if (isNotNull(o.getPayRollname())) {
				condition.append(" and t.payRollname like ? ");
				args.add(o.getPayRollname());
			}
			
			if (isNotNull(o.getHeight())) {
				condition.append(" and t.height >= ? ");
				args.add(o.getHeight());
			}
			if (isNotNull(o.getHeight2())) {
				condition.append(" and t.height <= ? ");
				args.add(o.getHeight2());
			}
			
			if (isNotNull(o.getWeight())) {
				condition.append(" and t.weight >= ? ");
				args.add(o.getWeight());
			}
			if (isNotNull(o.getWeight2())) {
				condition.append(" and t.weight <= ? ");
				args.add(o.getWeight2());
			}
		
		}
	}
	
	@Override
	public List<QSLCoach> findAllBySearchWithPage(QSLCoach o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from QSLCoach t where 1=1 ";
		String countQl = "select count(*) from QSLCoach t where 1=1";

		makeCondition(o,condition,args);

		List<QSLCoach> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.id,o.firstName from QSLCoach o where o.firstName like ?";
		String hql2 = "select count(*) from QSLCoach o where o.firstName like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<QSLCoach> findAllByIds(java.io.Serializable[] cids){
		String hql = "from QSLCoach o where o.id in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<QSLCoach> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(QSLCoach o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from QSLCoach t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}

	@Override
	public List<QSLCoach> findTopN(Integer n) {
		String hql = "from QSLCoach t order by t.enrollDate desc";
		return ifindTopNByQL(hql, n);
	}
}
