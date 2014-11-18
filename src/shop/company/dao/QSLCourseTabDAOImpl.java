package shop.company.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLCourseTab;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class QSLCourseTabDAOImpl extends AbstractDAO<QSLCourseTab> implements
		BaseDAO<QSLCourseTab>, QSLCourseTabDAO {

	public QSLCourseTabDAOImpl() {
		super(QSLCourseTab.class);
	}

    public void makeCondition(QSLCourseTab o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){
			if (isNotNull(o.getDescription())) {
				condition.append(" and t.description like ? ");
				args.add("%"+o.getDescription()+"%");
			}
			if (isNotNull(o.getId())) {
				condition.append(" and t.id = ? ");
				args.add(o.getId());
			}
			if (isNotNull(o.getYear())) {
				condition.append(" and t.year = ? ");
				args.add(o.getYear());
			}
			if (isNotNull(o.getMonth())) {
				condition.append(" and t.month = ? ");
				args.add(o.getMonth());
			}
			if (isNotNull(o.getTitle())) {
				condition.append(" and t.title like ? ");
				args.add("%"+o.getTitle()+"%");
			}
		}
	}
	
	@Override
	public List<QSLCourseTab> findAllBySearchWithPage(QSLCourseTab o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from QSLCourseTab t where 1=1 ";
		String countQl = "select count(*) from QSLCourseTab t where 1=1";

		makeCondition(o,condition,args);

		List<QSLCourseTab> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.id,o.id from QSLCourseTab o where o.id like ?";
		String hql2 = "select count(*) from QSLCourseTab o where o.id like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<QSLCourseTab> findAllByIds(java.io.Serializable[] cids){
		String hql = "from QSLCourseTab o where o.id in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<QSLCourseTab> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(QSLCourseTab o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from QSLCourseTab t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}

	@Override
	public QSLCourseTab findAllByYearAndMonth(Integer year, Integer month) {
		String hql = "from QSLCourseTab t where t.year=? and month=? ";
		List<QSLCourseTab> list = ifindByQL(hql, year,month);
		if(null == list || 0 == list.size()){
			hql = "from QSLCourseTab t where t.id=? ";
			list = ifindByQL(hql, 1);
		}
		if(null == list || 0 == list.size()){
			return null;
		}
		return list.get(0);
	}
}
