package shop.company.dao;

import java.util.*;

import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLCoach;
import shop.company.entity.QSLVenues;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class QSLVenuesDAOImpl extends AbstractDAO<QSLVenues> implements
		BaseDAO<QSLVenues>, QSLVenuesDAO {

	public QSLVenuesDAOImpl() {
		super(QSLVenues.class);
	}
	
	public void makeCondition(QSLVenues o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){
			if (isNotNull(o.getName())) {
				condition.append(" and t.name like ? ");
				args.add("%"+o.getName()+"%");
			}
			if (isNotNull(o.getDescription())) {
				condition.append(" and t.description like ? ");
				args.add("%"+o.getDescription()+"%");
			}
		}
	}
	
	@Override
	public List<QSLVenues> findAllBySearchWithPage(QSLVenues o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from QSLVenues t where 1=1 ";
		String countQl = "select count(*) from QSLVenues t where 1=1";

		makeCondition(o,condition,args);

		List<QSLVenues> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.id,o.id from QSLVenues o where o.id like ?";
		String hql2 = "select count(*) from QSLVenues o where o.id like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<QSLVenues> findAllByIds(java.io.Serializable[] cids){
		String hql = "from QSLVenues o where o.id in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<QSLVenues> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(QSLVenues o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from QSLVenues t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}

	@Override
	public List<QSLVenues> findTopN(Integer n) {
		String hql = "from QSLVenues t where t.showFlag<>'N' order by t.id desc";
		return ifindTopNByQL(hql, n);
	}
}
