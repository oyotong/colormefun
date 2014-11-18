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
import shop.company.entity.AuthPurview;
import shop.company.entity.AuthUser;

@Repository
@AutoDAO
public class AuthPurviewDAOImpl extends AbstractDAO<AuthPurview> implements
		BaseDAO<AuthPurview>, AuthPurviewDAO {

	public AuthPurviewDAOImpl() {
		super(AuthPurview.class);
	}
	
	@Override
	public List<AuthPurview> findAllBySearchWithPage(AuthPurview o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from AuthPurview t where 1=1 ";
		String countQl = "select count(*) from AuthPurview t where 1=1";

		if (isNotNull(o) ){

			if (isNotNull(o.getPurviewComment())) {
				condition.append(" and t.purviewComment=? ");
				args.add(o.getPurviewComment());
			}
			if (isNotNull(o.getPurviewUrl())) {
				condition.append(" and t.purviewUrl like ? ");
				args.add("%"+o.getPurviewUrl()+"%");
			}
			if (isNotNull(o.getPurviewName())) {
				condition.append(" and t.purviewName like ? ");
				args.add("%"+o.getPurviewName()+"%");
			}
			if (isNotNull(o.getPurviewType())) {
				condition.append(" and t.purviewType=? ");
				args.add(o.getPurviewType());
			}
			if (isNotNull(o.getPurviewId())) {
				condition.append(" and t.purviewId=? ");
				args.add(o.getPurviewId());
			}
			if (isNotNull(o.getPurviewMask())) {
				condition.append(" and t.purviewMask=? ");
				args.add(o.getPurviewMask());
			}
			if (isNotNull(o.getPurviewImage())) {
				condition.append(" and t.purviewImage=? ");
				args.add(o.getPurviewImage());
			}
            if (isNotNull(o.getActive())) {
                condition.append(" and t.active=? ");
                args.add(o.getActive());
            }
		
		}
		
		hql += condition + " order by t.purviewMask";
		

		List<AuthPurview> instance = ifindByQLWithPage(hql, countQl
				+ condition, args.toArray(new String[0]));
		
		return instance;
	}
	
	@Override
	public List<AuthPurview> findAllWithPage() {
		String hql = "select t from AuthPurview t order by t.purviewMask";
		String countQl = "select count(*) from AuthPurview t";
		List<AuthPurview> instance = ifindByQLWithPage(hql,countQl);
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.purviewId,o.purviewName from AuthPurview o where o.purviewName like ?";
		String hql2 = "select count(*) from AuthPurview o where o.purviewName like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<AuthPurview> findAllByIds(java.io.Serializable[] cids){
		String hql = "from AuthPurview o where o.purviewId in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<AuthPurview> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public List<AuthPurview> findAllParentsWithKids() {
		String hql = "select distinct t from AuthPurview t join fetch t.authPurviews where t.authPurview is null order by t.purviewMask";
		return getEntityManager().createQuery(hql).getResultList();
	}

	@Override
	public List<AuthPurview> findAllParentsAndKids() {
		String sql = "select * from auth_purview t order by t.PURVIEW_MASK";
		return getEntityManager().createNativeQuery(sql, AuthPurview.class).getResultList();
	}

	@Override
	public List<AuthPurview> findAllParentAndKidsByUser(AuthUser u) {
		String sql = null;
			/*"select distinct t.*\n" +
			"  from AUTH_USER_ROLE ur, AUTH_ROLE_PURVIEW rt, AUTH_PURVIEW t\n" + 
			" where ur.USER_id = '"+u.getUserName()+"'\n" + 
			"   and ur.ROLE_ID = rt.ROLE_ID\n" + 
			"   and ((rt.PURVIEW_ID = t.PURVIEW_ID and t.PURVIEW_URL <> '/todo.jsp') or\n" + 
			"       rt.PURVIEW_ID in\n" + 
			"       (select p.PURVIEW_ID\n" + 
			"           from AUTH_PURVIEW p\n" + 
			"          where p.PURVIEW_UPID = t.PURVIEW_ID))\n" + 
			" order by t.PURVIEW_MASK";*/
			
			if("admin".equalsIgnoreCase(u.getUserName())){
				
				sql = "select t.* from auth_purview t  where t.active='Y' order by t.PURVIEW_MASK";
				
			}else{
			
				sql = "select distinct t.*\n" +
				"  from auth_user_role ur, auth_role_purview rt, auth_purview t\n" + 
				" where ur.USER_id = '"+u.getUserName()+"'\n" + 
				"   and ur.ROLE_ID = rt.ROLE_ID\n" + 
				"   and ((rt.PURVIEW_ID = t.PURVIEW_ID and t.PURVIEW_URL not like 'todo%' and t.active='Y') or\n" +
				"       rt.PURVIEW_ID in\n" + 
				"       (select p.PURVIEW_ID\n" + 
				"           from auth_purview p\n" + 
				"          where p.PURVIEW_UPID = t.PURVIEW_ID))\n" + 
				" order by t.PURVIEW_MASK";
			}

		return getEntityManager().createNativeQuery(sql, AuthPurview.class).getResultList();
	}

	public String findFTree(StringBuffer sb, String mid) {
		return null;
	}

	public String findATree(StringBuffer sb, String mid) {
		return null;
	}

}
