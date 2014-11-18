package shop.company.dao;

import java.util.*;

import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLProduct;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class QSLProductDAOImpl extends AbstractDAO<QSLProduct> implements
		BaseDAO<QSLProduct>, QSLProductDAO {

	public QSLProductDAOImpl() {
		super(QSLProduct.class);
	}
	
	public void makeCondition(QSLProduct o, StringBuffer condition,
			List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getContent())) {
				condition.append(" and t.content like ? ");
				args.add("%"+o.getContent()+"%");
			}
			
			if (isNotNull(o.getDescription())) {
				condition.append(" and t.description like ? ");
				args.add("%"+o.getDescription()+"%");
			}
			
			if (isNotNull(o.getFavor())) {
				condition.append(" and t.favor like ? ");
				args.add("%"+o.getFavor()+"%");
			}
			
			if (isNotNull(o.getName())) {
				condition.append(" and t.name like ? ");
				args.add("%"+o.getName()+"%");
			}
			
			if (isNotNull(o.getId())) {
				condition.append(" and t.id=? ");
				args.add(o.getId());
			}
						
			if (isNotNull(o.getShowFlag())) {
				condition.append(" and t.showFlag=? ");
				args.add(o.getShowFlag());
			}
			if (isNotNull(o.getTopFlag())) {
				condition.append(" and t.topFlag=? ");
				args.add(o.getTopFlag());
			}
		
		}
	}
	
	@Override
	public List<QSLProduct> findAllBySearchWithPage(QSLProduct o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from QSLProduct t where 1=1 ";
		String countQl = "select count(*) from QSLProduct t where 1=1";

		makeCondition(o,condition,args);

		List<QSLProduct> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}

    @Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.id,o.id from QSLProduct o where o.id like ?";
		String hql2 = "select count(*) from QSLProduct o where o.id like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<QSLProduct> findAllByIds(java.io.Serializable[] cids){
		String hql = "from QSLProduct o where o.id in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<QSLProduct> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(QSLProduct o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from QSLProduct t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}

	@Override
	public List<QSLProduct> findTopNProduct(Integer n) {
		String hql = "from QSLProduct t where t.showFlag = 'Y' order by t.topFlag desc";
		return ifindTopNByQL(hql, n);
	}
}
