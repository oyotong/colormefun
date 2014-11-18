package shop.company.dao;

import java.util.*;

import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLNews;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class QSLNewsDAOImpl extends AbstractDAO<QSLNews> implements
		BaseDAO<QSLNews>, QSLNewsDAO {

	public QSLNewsDAOImpl() {
		super(QSLNews.class);
	}
	
	public void makeCondition(QSLNews o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getTitle())) {
				condition.append(" and t.title like ? ");
				args.add("%"+o.getTitle()+"%");
			}
			if (isNotNull(o.getShowFlag())) {
				condition.append(" and t.showFlag=? ");
				args.add(o.getShowFlag());
			}
			if (isNotNull(o.getEntryDatetime())) {
				condition.append(" and t.entryDatetime>=? ");
				args.add(o.getEntryDatetime());
			}
			if (isNotNull(o.getEntryDatetime2())) {
				condition.append(" and t.entryDatetime<=? ");
				args.add(o.getEntryDatetime());
			}
			if (isNotNull(o.getTopFlag())) {
				condition.append(" and t.topFlag=? ");
				args.add(o.getTopFlag());
			}
			if (isNotNull(o.getEntryId())) {
				condition.append(" and t.entryId=? ");
				args.add(o.getEntryId());
			}
			if (isNotNull(o.getCategoryType())) {
				condition.append(" and t.categoryType=? ");
				args.add(o.getCategoryType());
			}
		
		}
	}
	
	@Override
	public List<QSLNews> findAllBySearchWithPage(QSLNews o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from QSLNews t where 1=1 ";
		String countQl = "select count(*) from QSLNews t where 1=1";

		makeCondition(o,condition,args);

		String orderBy = " order by t.topFlag, t.entryDatetime desc ";
		
		List<QSLNews> instance = ifindByQLWithPage(hql + condition + orderBy, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.id,o.id from QSLNews o where o.id like ?";
		String hql2 = "select count(*) from QSLNews o where o.id like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<QSLNews> findAllByIds(java.io.Serializable[] cids){
		String hql = "from QSLNews o where o.id in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<QSLNews> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(QSLNews o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from QSLNews t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}

	@Override
	public List<QSLNews> findTopNNews(String type, int n) {
		String hql = "from QSLNews t where t.showFlag = 'Y' and t.categoryType=? order by t.topFlag, t.entryDatetime desc";
		
		return ifindTopNByQL(hql, n, type);
	}

}
