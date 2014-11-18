package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import shop.company.entity.SiteParameter;

public interface SiteParameterService {
	
	public void saveSiteParameter(SiteParameter o);
	
	public void updateSiteParameter(SiteParameter o);
	
	public void deleteSiteParameter(SiteParameter o);
	
	public void deleteSiteParameterByIds(String[] ids);
	
	public SiteParameter getSiteParameterById(Serializable id);
	
	public List<SiteParameter> findAllSiteParameter();
	
	public List<SiteParameter> findAllSiteParameterWithPage();
	
	public List<SiteParameter> findAllSiteParameterBySearchWithPage(SiteParameter o);
	
	public Map findAllSiteParameterByNameWithIdAndName(String name);
	
	public List<SiteParameter> findAllSiteParameterByIds(String[] cids);
	
}
