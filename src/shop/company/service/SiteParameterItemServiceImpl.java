package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.common.exception.ApplicationException;
import shop.company.dao.SiteParameterItemDAO;
import shop.company.entity.SiteParameterItem;

@Service
@Transactional
public class SiteParameterItemServiceImpl implements SiteParameterItemService {

	@Autowired()
	private SiteParameterItemDAO siteParameterItemDAO;

	public SiteParameterItemDAO getSiteParameterItemDAO() {
		return siteParameterItemDAO;
	}

	public void setSiteParameterItemDAO(
            SiteParameterItemDAO siteParameterItemDAO) {
		this.siteParameterItemDAO = siteParameterItemDAO;
	}

	@Override
	public void deleteSiteParameterItem(SiteParameterItem o) {
		siteParameterItemDAO.remove(o);
	}

	@Override
	public void deleteSiteParameterItemByIds(Integer[] ids) {
		siteParameterItemDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SiteParameterItem> findAllSiteParameterItem() {
		return siteParameterItemDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<SiteParameterItem> findAllSiteParameterItemWithPage() {
		return siteParameterItemDAO.findAllWithPage();
	}

	@Override
	@Transactional(readOnly = true)
	public List<SiteParameterItem> findAllSiteParameterItemBySearchWithPage(
			SiteParameterItem o) {
		return siteParameterItemDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public SiteParameterItem getSiteParameterItemById(Serializable id) {
		return siteParameterItemDAO.get(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Map findAllSiteParameterItemByNameWithIdAndName(String name) {
		return siteParameterItemDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveSiteParameterItem(SiteParameterItem o) {
		siteParameterItemDAO.persist(o);
	}

	@Override
	public void updateSiteParameterItem(SiteParameterItem o) {
		siteParameterItemDAO.merge(o);
	}

	@Override
	public List<SiteParameterItem> findAllSiteParameterItemByIds(
			Integer[] cids) {
		return siteParameterItemDAO.findAllByIds(cids);
	}

	@Override
	public List<SiteParameterItem> findSiteParameterItemByParameter(
			String parameter) {
		return siteParameterItemDAO.findByParameter(parameter);
	}

	@Override
	public SiteParameterItem getSiteParameterItemByParameterAndKey(
			String parameter, String key) {
		return siteParameterItemDAO.getByParameterAndKey(parameter, key);
	}

	@Override
	public void saveQkshouxufei(List<SiteParameterItem> siteParameterItemList) {
		for (SiteParameterItem i : siteParameterItemList) {
			i.setParamKey("DRAWING_HANDING_CHARGE");
			siteParameterItemDAO.merge(i);
		}
	}
	
	@Override
	public void updateAll(List<SiteParameterItem> siteParameterItemList) {
		for (SiteParameterItem i : siteParameterItemList) {
			siteParameterItemDAO.merge(i);
		}
	}

	@Override
	public void saveAll(List<SiteParameterItem> list) {
		if (null == list || list.size() == 0) {
			throw new ApplicationException("�޿��޸ĵĲ���");
		}
		List<SiteParameterItem> list2 = findSiteParameterItemByParameter(list
				.get(0).getParamKey());
		for (SiteParameterItem i : list2) {
			siteParameterItemDAO.remove(i);
		}
		int k = 0;
		for (SiteParameterItem i : list) {
			if (null == i.getParamItemValue())
				continue;
			if (null == i.getParamItemName())
				i.setParamItemName("" + ++k);
			siteParameterItemDAO.merge(i);
		}
	}

}
