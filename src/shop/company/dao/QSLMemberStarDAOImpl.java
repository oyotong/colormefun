package shop.company.dao;

import java.util.*;

import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLMemberStar;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class QSLMemberStarDAOImpl extends AbstractDAO<QSLMemberStar> implements
		BaseDAO<QSLMemberStar>, QSLMemberStarDAO {

	public QSLMemberStarDAOImpl() {
		super(QSLMemberStar.class);
	}
	
	public void makeCondition(QSLMemberStar o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getContent())) {
				condition.append(" and t.content like ? ");
				args.add("%"+o.getContent()+"%");
			}
			
			if (isNotNull(o.getEmployer())) {
				condition.append(" and t.employer like ? ");
				args.add("%"+o.getEmployer()+"%");
			}
			
			if (isNotNull(o.getTitle())) {
				condition.append(" and t.title like ? ");
				args.add("%"+o.getTitle()+"%");
			}
			
			if (isNotNull(o.getYear())) {
				condition.append(" and t.year = ? ");
				args.add(o.getYear());
			}
			
			if (isNotNull(o.getMonth())) {
				condition.append(" and t.month = ? ");
				args.add(o.getMonth());
			}
			
			if (isNotNull(o.getSex())) {
				condition.append(" and t.sex = ? ");
				args.add(o.getSex());
			}
			
			if (isNotNull(o.getWaistlineAfter())) {
				condition.append(" and t.waistlineAfter=? ");
				args.add(o.getWaistlineAfter());
			}
			if (isNotNull(o.getFatAfter())) {
				condition.append(" and t.fatAfter=? ");
				args.add(o.getFatAfter());
			}
			if (isNotNull(o.getWeightAfter())) {
				condition.append(" and t.weightAfter=? ");
				args.add(o.getWeightAfter());
			}
			if (isNotNull(o.getScoreAfter())) {
				condition.append(" and t.scoreAfter=? ");
				args.add(o.getScoreAfter());
			}
		
		}
	}
	
	@Override
	public List<QSLMemberStar> findAllBySearchWithPage(QSLMemberStar o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from QSLMemberStar t where 1=1 ";
		String countQl = "select count(*) from QSLMemberStar t where 1=1";

		makeCondition(o,condition,args);

		List<QSLMemberStar> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.id,o.id from QSLMemberStar o where o.id like ?";
		String hql2 = "select count(*) from QSLMemberStar o where o.id like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<QSLMemberStar> findAllByIds(java.io.Serializable[] cids){
		String hql = "from QSLMemberStar o where o.id in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<QSLMemberStar> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(QSLMemberStar o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from QSLMemberStar t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}

	@Override
	public List<QSLMemberStar> findTopNStar(Integer n) {
		String hql = "from QSLMemberStar t where t.showflag <> 'N' order by t.year desc, t.month desc";
		return ifindTopNByQL(hql, n);
	}
}
