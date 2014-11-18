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
import shop.company.entity.AuthUser;

@Repository
@AutoDAO
public class UserDAOImpl extends AbstractDAO<AuthUser> implements
		BaseDAO<AuthUser>, UserDAO {

	public UserDAOImpl() {
		super(AuthUser.class);
	}

	@Override
	public List<AuthUser> findAllBySearchWithPage(AuthUser o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from User t where 1=1 ";
		String countQl = "select count(*) from User t where 1=1";

		if (isNotNull(o)) {

			if (isNotNull(o.getUserPassword())) {
				condition.append(" and t.userPassword=? ");
				args.add(o.getUserPassword());
			}
			if (isNotNull(o.getUserName())) {
				condition.append(" and t.userName=? ");
				args.add(o.getUserName());
			}
			if (isNotNull(o.getUserRealname())) {
				condition.append(" and t.userRealname=? ");
				args.add(o.getUserRealname());
			}

		}

		List<AuthUser> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new String[0]));

		return instance;
	}

	@Override
	public Map findAllByNameWithIdAndName(String name) {
		String hql1 = "select o.userName,o.userRealname from User o where o.userRealname like ?";
		String hql2 = "select count(*) from User o where o.userRealname like ?";
		List instance = ifindByQLWithPage(hql1, hql2, "%"
				+ name.replace('.', '_') + "%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>) instance);
	}

	@Override
	public List<AuthUser> findAllByIds(java.io.Serializable[] cids) {
		String hql = "from User o where o.userName in "
				+ Arrays.toString(cids).replaceAll("\\[", "(").replaceAll(
						"\\]", ")").replaceAll("[^\\,\\(\\)]+", "?");
		List<AuthUser> instance = ifindByQL(hql, cids);
		return instance;
	}

	public String findFTree(StringBuffer sb, String mid) {
		return null;
	}

	public String findATree(StringBuffer sb, String mid) {
		return null;
	}
}
