package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.SiteParameterDAO;
import shop.company.entity.SiteParameter;

@Service
@Transactional
public class SiteParameterServiceImpl implements SiteParameterService {

	@Autowired()
	private SiteParameterDAO siteParameterDAO;

	public SiteParameterDAO getSiteParameterDAO() {
		return siteParameterDAO;
	}

	public void setSiteParameterDAO(SiteParameterDAO siteParameterDAO) {
		this.siteParameterDAO = siteParameterDAO;
	}

	@Override
	public void deleteSiteParameter(SiteParameter o) {
		siteParameterDAO.remove(o);
	}
	
	@Override
	public void deleteSiteParameterByIds(String[] ids){
		siteParameterDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SiteParameter> findAllSiteParameter() {
		return siteParameterDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SiteParameter> findAllSiteParameterWithPage(){
		return siteParameterDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SiteParameter> findAllSiteParameterBySearchWithPage(SiteParameter o){
		return siteParameterDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public SiteParameter getSiteParameterById(Serializable id) {
		return siteParameterDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllSiteParameterByNameWithIdAndName(String name) {
		return siteParameterDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveSiteParameter(SiteParameter o) {
		siteParameterDAO.persist(o);
	}

	@Override
	public void updateSiteParameter(SiteParameter o) {
		siteParameterDAO.merge(o);
	}
	
	@Override
	public List<SiteParameter> findAllSiteParameterByIds(String[] cids) {
		return siteParameterDAO.findAllByIds(cids);
	}

}
