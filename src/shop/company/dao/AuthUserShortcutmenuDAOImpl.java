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
import shop.company.entity.AuthUserShortcutmenu;

@Repository
@AutoDAO
public class AuthUserShortcutmenuDAOImpl extends
		AbstractDAO<AuthUserShortcutmenu> implements
		BaseDAO<AuthUserShortcutmenu>, AuthUserShortcutmenuDAO {

	public AuthUserShortcutmenuDAOImpl() {
		super(AuthUserShortcutmenu.class);
	}

	@Override
	public List<AuthUserShortcutmenu> findAllBySearchWithPage(
			AuthUserShortcutmenu o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from AuthUserShortcutmenu t where 1=1 ";
		String countQl = "select count(*) from AuthUserShortcutmenu t where 1=1";

		if (isNotNull(o)) {

			if (isNotNull(o.getShortMenuId())) {
				condition.append(" and t.shortMenuId=? ");
				args.add(o.getShortMenuId());
			}

		}

		List<AuthUserShortcutmenu> instance = ifindByQLWithPage(
				hql + condition, countQl + condition,
				args.toArray(new String[0]));

		return instance;
	}

	@Override
	public Map findAllByNameWithIdAndName(String name) {
		String hql1 = "select o.shortMenuId,o.shortMenuId from AuthUserShortcutmenu o where o.shortMenuId like ?";
		String hql2 = "select count(*) from AuthUserShortcutmenu o where o.shortMenuId like ?";
		List instance = ifindByQLWithPage(hql1, hql2,
				"%" + name.replace('.', '_') + "%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>) instance);
	}

	@Override
	public List<AuthUserShortcutmenu> findAllByIds(java.io.Serializable[] cids) {
		String hql = "from AuthUserShortcutmenu o where o.shortMenuId in "
				+ Arrays.toString(cids).replaceAll("\\[", "(")
						.replaceAll("\\]", ")")
						.replaceAll("[^\\,\\(\\)]+", "?");
		List<AuthUserShortcutmenu> instance = ifindByQL(hql, cids);
		return instance;
	}

	@Override
	public List<AuthUserShortcutmenu> findAllByUser(AuthUser user) {
		String hql = "from AuthUserShortcutmenu m where m.menu.purviewId in ( select distinct ps.purviewId\n"
				+ "  from AuthUser u join u.authRoles rs join rs.authPurviews ps\n"
				+ " where u.userName = ? and ps.purviewUrl not like 'todo%')\n"
				+ " order by m.shortMenuId";

		return ifindByQL(hql, user.getUserName());
	}

}
