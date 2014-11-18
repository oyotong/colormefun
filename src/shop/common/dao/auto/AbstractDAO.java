package shop.common.dao.auto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import shop.common.context.ApplicationContext;
import shop.common.util.page.Pagination;
import shop.company.entity.QSLProduct;

public abstract class AbstractDAO<T> implements BaseDAO<T> {

	private Class<T> type;

	@PersistenceContext
	private EntityManager entityManager;
	
//	public AbstractDAO(){
//		type = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//	}

	public AbstractDAO(Class<T> type) {
		super();
		this.type = type;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void persist(T transientInstance) {
		entityManager.merge(transientInstance);
	}

	public void remove(T persistentInstance) {
		entityManager.remove(persistentInstance);
	}
	
	public void removeByIds(java.io.Serializable[] ids){
		for(java.io.Serializable id : ids){
			T persistentInstance = get(id);
			entityManager.remove(persistentInstance);
		}
	}

	public T merge(T detachedInstance) {
		return entityManager.merge(detachedInstance);
	}

	public T get(java.io.Serializable id) {
		return entityManager.find(type, id);
	}

	public List<T> findAll() {

		List<T> instance = ifindByQL("select t from " + type.getSimpleName()
				+ " t");
		return instance;
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public List<T> ifindByQueryName(String name, Object... args) {

		Query query = getEntityManager().createNamedQuery(name);

		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i + 1, args[i]);
			}
		}
		return query.getResultList();

	}

	public T igetByQueryName(String name, Object... args) {

		List<T> list = ifindByQueryName(name, args);
		if (null == list || 0 == list.size()) {
			return null;
		}
		return list.get(0);

	}

	public T igetByQueryNameWithPage(String name, Object... args) {

		List<T> list = ifindByQueryNameWithPage(name, args);
		if (null == list || 0 == list.size()) {
			return null;
		}
		return list.get(0);

	}

	@SuppressWarnings("unchecked")
	public List<T> ifindByQueryNameWithPage(String name, Object... args) {
		
		Pagination pagination = ApplicationContext.getContext().getPagination();

		String pageName = name + "Count";

		Query query = getEntityManager().createNamedQuery(pageName);

		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i + 1, args[i]);
			}
		}
		
		Integer totalRowCount = Integer.parseInt(query.getSingleResult() + "");
		pagination.setTotalRowCount(totalRowCount);

		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i + 1, args[i]);
			}
		}
		return query.getResultList();

	}

    @SuppressWarnings("unchecked")
	@Override
	public List<T> ifindByQL(String ql, Object... args) {

		Query query = getEntityManager().createQuery(ql);

		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i+1, args[i]);
			}
		}
		return query.getResultList();

	}

	public T igetByQL(String ql, Object... args) {

		List<T> list = ifindByQL(ql, args);
		if (null == list || 0 == list.size()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<T> findAllWithPage() {
		String hql = "select t from "
			+ type.getSimpleName() + " t";
		String countQl = "select count(*) from "
			+ type.getSimpleName() + " t";
		List<T> instance = ifindByQLWithPage(hql,countQl);
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> ifindByQLWithPage(String ql,String countQl,Object... args) {

		Pagination pagination = ApplicationContext.getContext().getPagination();
		if(null == pagination){
			return ifindByQL(ql, args);
		}
		
		if(!pagination.isParameterChange()){
			ql = pagination.getQl();
			countQl = pagination.getCountQl();
		}else{
			pagination.setQl(ql);
			pagination.setCountQl(countQl);
		}

		// Order By
		String[] orderBys = pagination.getOrderBys();
		if(orderBys != null && orderBys.length > 0){
			ql = ql.replaceAll("\\s+", " ");
			int idx = ql.toLowerCase().indexOf("order by");
			String orderBy = " order by ";
			String oldOrderBy = "";
			if(idx != -1){
				oldOrderBy = ql.substring(idx+"order by".length());
				ql = ql.substring(0,idx);
			}
			for (String od : orderBys) {
				if(od.trim().length() > 1) orderBy += "t."+od+", ";
			}
			orderBy += oldOrderBy;
			
			if(orderBy.endsWith(", ")) orderBy = orderBy.substring(0,orderBy.length()-2);
			
			if(!" order by ".equals(orderBy)) ql += orderBy;
		}
				
		Query query = getEntityManager().createQuery(countQl);

		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i+1, args[i]);
			}
		}

		Integer totalRowCount = Integer.parseInt(query.getSingleResult() + "");
		pagination.setTotalRowCount(totalRowCount);

		query = getEntityManager().createQuery(ql);

		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i+1, args[i]);
			}
		}

		query.setFirstResult(pagination.getStartRow());
		query.setMaxResults(pagination.getRowCount());
		
		return query.getResultList();

	}
	
	protected List<T> ifindTopNByQL(String hql, int n, Object ... obj) {
		Query q = getEntityManager().createQuery(hql);
		
		for (Object o : obj) {
			q.setParameter(1, o);
		}
		
		q.setFirstResult(0);
		q.setMaxResults(n);
		return (List<T>)q.getResultList();
	}
	
	protected boolean isNotNull(Object obj){
		
		if(null == obj){
			return false;
		}
		
		if(obj instanceof Number  && ((Number)obj).intValue() == 0){
			return false;
		}
		
		if(obj instanceof Boolean  && ((Boolean)obj) == false){
			return false;
		}
		
		if(obj instanceof String && "".equals(((String)obj).trim())){
			return false;
		}
		
		if(obj instanceof Collection && ((Collection)obj).size() == 0){
			return false;
		}
		
		return true;
	}

    public void makeCondition(T o, StringBuffer condition, List args){

    }

    @Override
    public List<T> findAllBySearch(T o) {
        List args = new ArrayList();
        StringBuffer condition = new StringBuffer(500);

        String hql = "select t from "+type.getSimpleName()+" t where 1=1 ";

        makeCondition(o,condition,args);

        List<T> instance = ifindByQL(hql + condition, args.toArray(new Object[0]));

        return instance;
    }
}
