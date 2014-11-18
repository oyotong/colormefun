package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import shop.company.entity.SiteParameterItem;

public interface SiteParameterItemService {
	
	public void saveSiteParameterItem(SiteParameterItem o);
	
	public void updateSiteParameterItem(SiteParameterItem o);
	
	public void deleteSiteParameterItem(SiteParameterItem o);
	
	public void deleteSiteParameterItemByIds(Integer[] ids);
	
	public SiteParameterItem getSiteParameterItemById(Serializable id);
	
	public SiteParameterItem getSiteParameterItemByParameterAndKey(String parameter, String key);
	
	public List<SiteParameterItem> findSiteParameterItemByParameter(String parameter);
	
	public List<SiteParameterItem> findAllSiteParameterItem();
	
	public List<SiteParameterItem> findAllSiteParameterItemWithPage();
	
	public List<SiteParameterItem> findAllSiteParameterItemBySearchWithPage(SiteParameterItem o);
	
	public Map findAllSiteParameterItemByNameWithIdAndName(String name);
	
	public List<SiteParameterItem> findAllSiteParameterItemByIds(Integer[] cids);

	public void saveQkshouxufei(List<SiteParameterItem> siteParameterItemList);

	public void saveAll(List<SiteParameterItem> list);

	void updateAll(List<SiteParameterItem> siteParameterItemList);
	
}
