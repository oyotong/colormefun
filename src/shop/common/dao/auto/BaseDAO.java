package shop.common.dao.auto;

import java.util.List;
import java.util.Map;

public interface BaseDAO<T> {

	public abstract void persist(T obj);

	public abstract T merge(T obj);

	// public abstract void saveOrUpdate(T obj);
	public abstract void remove(T obj);
	
	public abstract void removeByIds(java.io.Serializable[] ids);

	public abstract T get(java.io.Serializable id);

	public abstract List<T> findAll();
	
	public abstract List<T> findAllWithPage();

	public abstract List<T> ifindByQL(String hql, Object... args);

	public abstract T igetByQL(String hql, Object... args);

	public abstract Class<T> getType();

	public abstract void setType(Class<T> type);

	public List<T> ifindByQueryName(String name, Object... args);

	public T igetByQueryName(String name, Object... args);
	
	public List<T> ifindByQLWithPage(String ql, String countQl, Object... args);
	
	public T igetByQueryNameWithPage(String name, Object... args);
	
	public List<T> ifindByQueryNameWithPage(String name, Object... args);
	
	public List<T> findAllBySearchWithPage(T o);

	public List<T> findAllBySearch(T o);

	public Map findAllByNameWithIdAndName(String name);
	
	public List<T> findAllByIds(java.io.Serializable[] cids);

}