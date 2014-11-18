package shop.company.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.common.util.ArraysUtils;
import shop.company.entity.SystemDictionary;

@Repository
@AutoDAO
public class SystemDictionaryDAOImpl extends AbstractDAO<SystemDictionary> implements
		BaseDAO<SystemDictionary>, SystemDictionaryDAO {

	public SystemDictionaryDAOImpl() {
		super(SystemDictionary.class);
	}
	
	@Override
	public SystemDictionary get(Serializable id) {
		String hql = "select distinct t from SystemDictionary t left join fetch t.systemDictionaryItems ti where t.dictId=?";
		return igetByQL(hql, id);
	}
	
	@Override
	public List<SystemDictionary> findAllBySearchWithPage(SystemDictionary o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from SystemDictionary t where 1=1 ";
		String countQl = "select count(*) from SystemDictionary t where 1=1";

		if (isNotNull(o) ){

			if (isNotNull(o.getDictId())) {
				condition.append(" and t.dictId=? ");
				args.add(o.getDictId());
			}
			if (isNotNull(o.getDictComment())) {
				condition.append(" and t.dictComment=? ");
				args.add(o.getDictComment());
			}
			if (isNotNull(o.getDictName())) {
				condition.append(" and t.dictName=? ");
				args.add(o.getDictName());
			}
		
		}

		List<SystemDictionary> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new String[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.dictId,o.dictName from SystemDictionary o where o.dictName like ?";
		String hql2 = "select count(*) from SystemDictionary o where o.dictName like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<SystemDictionary> findAllByIds(java.io.Serializable[] cids){
		String hql = "from SystemDictionary o where o.dictId in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<SystemDictionary> instance = ifindByQL(hql ,cids);		
		return instance;
	}

	public String findFTree(StringBuffer sb, String mid) {
		return null;
	}

	public String findATree(StringBuffer sb, String mid) {
		return null;
	}
}
