package shop.company.dao;

import java.util.*;

import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLCoach;
import shop.company.entity.QSLCourse;
import shop.company.entity.QSLCourseTab;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class QSLCourseDAOImpl extends AbstractDAO<QSLCourse> implements
		BaseDAO<QSLCourse>, QSLCourseDAO {

	public QSLCourseDAOImpl() {
		super(QSLCourse.class);
	}
	
	public void makeCondition(QSLCourse o, StringBuffer condition,
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
			if (isNotNull(o.getName())) {
				condition.append(" and t.name like ? ");
				args.add("%"+o.getName()+"%");
			}
			if (isNotNull(o.getTimezonedefine())) {
				condition.append(" and t.timezonedefine = ? ");
				args.add(o.getTimezonedefine());
			}
			if (isNotNull(o.getVanues())) {
				condition.append(" and t.vanues = ? ");
				args.add(o.getVanues());
			}
			if (isNotNull(o.getWeekday())) {
				condition.append(" and t.weekday = ? ");
				args.add(o.getWeekday());
			}
			
			if (isNotNull(o.getCoach())) {
				QSLCoach c = o.getCoach();
				if (isNotNull(c.getId())) {
					condition.append(" and t.coach.id = ? ");
					args.add(c.getId());
				}
			}
			
			if (isNotNull(o.getTable())) {
				QSLCourseTab c = o.getTable();
				if (isNotNull(c.getId())) {
					condition.append(" and t.table.id = ? ");
					args.add(c.getId());
				}
			}
		
		}
	}
	
	@Override
	public List<QSLCourse> findAllBySearchWithPage(QSLCourse o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from QSLCourse t where 1=1 ";
		String countQl = "select count(*) from QSLCourse t where 1=1";

		makeCondition(o,condition,args);

		List<QSLCourse> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.id,o.id from QSLCourse o where o.id like ?";
		String hql2 = "select count(*) from QSLCourse o where o.id like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<QSLCourse> findAllByIds(java.io.Serializable[] cids){
		String hql = "from QSLCourse o where o.id in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<QSLCourse> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(QSLCourse o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from QSLCourse t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}

	@Override
	public List<QSLCourse> findAllByTabId(Integer tabId) {
		String hql = "select distinct t from QSLCourse t where t.table.id=? order by t.vanues,t.timezonedefine,t.weekday";
		List<QSLCourse> list = ifindByQL(hql, tabId);
		return list;
	}
}
 