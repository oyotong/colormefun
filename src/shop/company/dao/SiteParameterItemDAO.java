package shop.company.dao;

import java.util.List;

import shop.common.dao.auto.BaseDAO;
import shop.company.entity.SiteParameterItem;

public interface SiteParameterItemDAO extends BaseDAO<SiteParameterItem>{

	List<SiteParameterItem> findByParameter(String parameter);

	SiteParameterItem getByParameterAndKey(String parameter, String key);
	
}