package shop.company.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.stereotype.Repository;

import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.common.util.ArraysUtils;
import shop.company.entity.SystemDictionaryItem;

@Repository
@AutoDAO
public class SystemDictionaryItemDAOImpl extends
		AbstractDAO<SystemDictionaryItem> implements
		BaseDAO<SystemDictionaryItem>, SystemDictionaryItemDAO {

	public SystemDictionaryItemDAOImpl() {
		super(SystemDictionaryItem.class);
	}

	@Override
	public List<SystemDictionaryItem> findAllBySearchWithPage(
			SystemDictionaryItem o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from SystemDictionaryItem t where 1=1 ";
		String countQl = "select count(*) from SystemDictionaryItem t where 1=1";

		if (isNotNull(o)) {

			if (isNotNull(o.getDictItemKey())) {
				condition.append(" and t.dictItemKey=? ");
				args.add(o.getDictItemKey());
			}
			if (isNotNull(o.getDictItemComment())) {
				condition.append(" and t.dictItemComment=? ");
				args.add(o.getDictItemComment());
			}
			if (isNotNull(o.getDictItemId())) {
				condition.append(" and t.dictItemId=? ");
				args.add(o.getDictItemId());
			}
			if (isNotNull(o.getDictItemValue())) {
				condition.append(" and t.dictItemValue=? ");
				args.add(o.getDictItemValue());
			}

		}

		List<SystemDictionaryItem> instance = ifindByQLWithPage(
				hql + condition, countQl + condition,
				args.toArray(new String[0]));

		return instance;
	}

	@Override
	public Map findAllByNameWithIdAndName(String name) {
		String hql1 = "select o.dictItemId,o.dictItemKey from SystemDictionaryItem o where o.dictItemKey like ?";
		String hql2 = "select count(*) from SystemDictionaryItem o where o.dictItemKey like ?";
		List instance = ifindByQLWithPage(hql1, hql2,
				"%" + name.replace('.', '_') + "%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>) instance);
	}

	@Override
	public List<SystemDictionaryItem> findAllByIds(java.io.Serializable[] cids) {
		String hql = "from SystemDictionaryItem o where o.dictItemId in "
				+ Arrays.toString(cids).replaceAll("\\[", "(")
						.replaceAll("\\]", ")")
						.replaceAll("[^\\,\\(\\)]+", "?");
		List<SystemDictionaryItem> instance = ifindByQL(hql, cids);
		return instance;
	}

	@Override
	public List<SystemDictionaryItem> findAllByDictId(String dictId) {
		String sql = "select * from system_dictionary_item o where o.dict_Id = ? order by o.dict_item_key, o.dict_item_id ";
		HibernateEntityManager em = (HibernateEntityManager) getEntityManager();
		Session s = em.getSession();
		return s.createSQLQuery(sql).addEntity(SystemDictionaryItem.class)
				.setCacheable(true).setCacheRegion("myCacheRegion")
				.setParameter(0, dictId).list();
	}

}
