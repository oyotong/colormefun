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
import shop.company.entity.SiteParameterItem;

@Repository
@AutoDAO
public class SiteParameterItemDAOImpl extends AbstractDAO<SiteParameterItem>
		implements BaseDAO<SiteParameterItem>, SiteParameterItemDAO {

	public SiteParameterItemDAOImpl() {
		super(SiteParameterItem.class);
	}

	@Override
	public List<SiteParameterItem> findAllBySearchWithPage(SiteParameterItem o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from SiteParameterItem t where 1=1 ";
		String countQl = "select count(*) from SiteParameterItem t where 1=1";

		if (isNotNull(o)) {

			if (isNotNull(o.getParamItemId())) {
				condition.append(" and t.paramItemId=? ");
				args.add(o.getParamItemId());
			}
			if (isNotNull(o.getParamItemName())) {
				condition.append(" and t.paramItemName=? ");
				args.add(o.getParamItemName());
			}
			if (isNotNull(o.getParamItemValue())) {
				condition.append(" and t.paramItemValue=? ");
				args.add(o.getParamItemValue());
			}
			if (isNotNull(o.getParamItemComment())) {
				condition.append(" and t.paramItemComment=? ");
				args.add(o.getParamItemComment());
			}
			if (isNotNull(o.getParamKey())) {
				condition.append(" and t.paramKey=? ");
				args.add(o.getParamKey());
			}

		}

		List<SiteParameterItem> instance = ifindByQLWithPage(hql + condition,
				countQl + condition, args.toArray(new String[0]));

		return instance;
	}

	@Override
	public Map findAllByNameWithIdAndName(String name) {
		String hql1 = "select o.paramItemId,o.paramKey from SiteParameterItem o where o.paramKey like ?";
		String hql2 = "select count(*) from SiteParameterItem o where o.paramKey like ?";
		List instance = ifindByQLWithPage(hql1, hql2,
				"%" + name.replace('.', '_') + "%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>) instance);
	}

	@Override
	public List<SiteParameterItem> findAllByIds(java.io.Serializable[] cids) {
		String hql = "from SiteParameterItem o where o.paramItemId in "
				+ Arrays.toString(cids).replaceAll("\\[", "(")
						.replaceAll("\\]", ")")
						.replaceAll("[^\\,\\(\\)]+", "?");
		List<SiteParameterItem> instance = ifindByQL(hql, cids);
		return instance;
	}

	@Override
	public List<SiteParameterItem> findByParameter(String parameter) {
		String hql1 = "select o from SiteParameterItem o where o.paramKey = ?";
		HibernateEntityManager em = (HibernateEntityManager) getEntityManager();
		Session s = em.getSession();
		return s.createQuery(hql1).setParameter(0, parameter)
				.setCacheable(true).list();//.setCacheRegion("myCacheRegion")
	}

	@Override
	public SiteParameterItem getByParameterAndKey(String parameter, String key) {
		String hql1 = "select o from SiteParameterItem o where o.paramKey = ? and o.paramItemName = ?";
		HibernateEntityManager em = (HibernateEntityManager) getEntityManager();
		Session s = em.getSession();
		return (SiteParameterItem) s.createQuery(hql1)
				.setParameter(0, parameter).setParameter(1, key)
				.setCacheable(true)
				.uniqueResult();
	}

}
