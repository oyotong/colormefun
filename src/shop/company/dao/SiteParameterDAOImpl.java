package shop.company.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.common.util.ArraysUtils;
import shop.company.entity.SiteParameter;

@Repository
@AutoDAO
public class SiteParameterDAOImpl extends AbstractDAO<SiteParameter>
		implements BaseDAO<SiteParameter>, SiteParameterDAO {

	public SiteParameterDAOImpl() {
		super(SiteParameter.class);
	}

	@Override
	public List<SiteParameter> findAllBySearchWithPage(SiteParameter o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from SiteParameter t where 1=1 ";
		String countQl = "select count(*) from SiteParameter t where 1=1";

		if (isNotNull(o)) {

			if (isNotNull(o.getParamComment())) {
				condition.append(" and t.paramComment=? ");
				args.add(o.getParamComment());
			}
			if (isNotNull(o.getParamKey())) {
				condition.append(" and t.paramKey=? ");
				args.add(o.getParamKey());
			}
			if (isNotNull(o.getParamName())) {
				condition.append(" and t.paramName=? ");
				args.add(o.getParamName());
			}

		}

		List<SiteParameter> instance = ifindByQLWithPage(hql + condition,
				countQl + condition, args.toArray(new String[0]));

		return instance;
	}

	@Override
	public Map findAllByNameWithIdAndName(String name) {
		String hql1 = "select o.paramKey,o.paramKey from SiteParameter o where o.paramKey like ?";
		String hql2 = "select count(*) from SiteParameter o where o.paramKey like ?";
		List instance = ifindByQLWithPage(hql1, hql2,
				"%" + name.replace('.', '_') + "%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>) instance);
	}

	@Override
	public List<SiteParameter> findAllByIds(java.io.Serializable[] cids) {
		String hql = "from SiteParameter o where o.paramKey in "
				+ Arrays.toString(cids).replaceAll("\\[", "(")
						.replaceAll("\\]", ")")
						.replaceAll("[^\\,\\(\\)]+", "?");
		List<SiteParameter> instance = ifindByQL(hql, cids);
		return instance;
	}

}
