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
import shop.company.entity.AuthRole;

@Repository
@AutoDAO
public class AuthRoleDAOImpl extends AbstractDAO<AuthRole> implements
		BaseDAO<AuthRole>, AuthRoleDAO {

	public AuthRoleDAOImpl() {
		super(AuthRole.class);
	}

	public void persist(AuthRole role) {

		if (null == role) {
			Integer maxId = Integer
					.parseInt((String) getEntityManager().createNativeQuery(
							"select max(r.role_Id) from Auth_Role r")
							.getSingleResult());

			role.setRoleId(maxId + 1 + "");
		}

		super.merge(role);
	}

	@Override
	public List<AuthRole> findAllBySearchWithPage(AuthRole o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from AuthRole t where 1=1 ";
		String countQl = "select count(*) from AuthRole t where 1=1";

		if (isNotNull(o)) {

			if (isNotNull(o.getRoleName())) {
				condition.append(" and t.roleName like ? ");
				args.add("%" + o.getRoleName() + "%");
			}
			if (isNotNull(o.getRoleId())) {
				condition.append(" and t.roleId=? ");
				args.add(o.getRoleId());
			}

		}

		List<AuthRole> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new String[0]));

		return instance;
	}

	@Override
	public Map findAllByNameWithIdAndName(String name) {
		String hql1 = "select o.roleId,o.roleName from AuthRole o where o.roleName like ?";
		String hql2 = "select count(*) from AuthRole o where o.roleName like ?";
		List instance = ifindByQLWithPage(hql1, hql2, "%"
				+ name.replace('.', '_') + "%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>) instance);
	}

	@Override
	public List<AuthRole> findAllByIds(java.io.Serializable[] cids) {
		String hql = "from AuthRole o where o.roleId in "
				+ Arrays.toString(cids).replaceAll("\\[", "(").replaceAll(
						"\\]", ")").replaceAll("[^\\,\\(\\)]+", "?");
		List<AuthRole> instance = ifindByQL(hql, cids);
		return instance;
	}

	@Override
	public List<AuthRole> findAllWithoutPurview() {
		String hql = "select new AuthRole(o.roleId,o.roleName) from AuthRole o";
		return ifindByQL(hql);
	}

	public String findFTree(StringBuffer sb, String mid) {
		return null;
	}

	public String findATree(StringBuffer sb, String mid) {
		return null;
	}
}
