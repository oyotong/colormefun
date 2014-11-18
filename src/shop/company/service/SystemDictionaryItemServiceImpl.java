package shop.company.service;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.common.context.ApplicationContext;
import shop.company.dao.SystemDictionaryItemDAO;
import shop.company.entity.SystemDictionaryItem;

@Service
@Transactional
public class SystemDictionaryItemServiceImpl implements SystemDictionaryItemService {

	private Map<String,List<SystemDictionaryItem>> dict = new Hashtable<String, List<SystemDictionaryItem>>();
	
	public SystemDictionaryItemServiceImpl() {
		ApplicationContext.getContext().addAttribute("DICT",dict);
	}
	
	@Autowired()
	private SystemDictionaryItemDAO systemDictionaryItemDAO;

	public SystemDictionaryItemDAO getSystemDictionaryItemDAO() {
		return systemDictionaryItemDAO;
	}

	public void setSystemDictionaryItemDAO(SystemDictionaryItemDAO systemDictionaryItemDAO) {
		this.systemDictionaryItemDAO = systemDictionaryItemDAO;
	}

	@Override
	public void deleteSystemDictionaryItem(SystemDictionaryItem o) {
		systemDictionaryItemDAO.remove(o);
	}
	
	@Override
	public void deleteSystemDictionaryItemByIds(Integer[] ids){
		systemDictionaryItemDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemDictionaryItem> findAllSystemDictionaryItem() {
		return systemDictionaryItemDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SystemDictionaryItem> findAllSystemDictionaryItemWithPage(){
		return systemDictionaryItemDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SystemDictionaryItem> findAllSystemDictionaryItemBySearchWithPage(SystemDictionaryItem o){
		return systemDictionaryItemDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public SystemDictionaryItem getSystemDictionaryItemById(Serializable id) {
		return systemDictionaryItemDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllSystemDictionaryItemByNameWithIdAndName(String name) {
		return systemDictionaryItemDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveSystemDictionaryItem(SystemDictionaryItem o) {
		systemDictionaryItemDAO.persist(o);
	}

	@Override
	public void updateSystemDictionaryItem(SystemDictionaryItem o) {
		systemDictionaryItemDAO.merge(o);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SystemDictionaryItem> findAllSystemDictionaryItemByIds(Integer[] cids) {
		return systemDictionaryItemDAO.findAllByIds(cids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemDictionaryItem> findSystemDictionaryItemByDictId(
			String dictId) {
		List<SystemDictionaryItem> item = dict.get(dictId);
		if(null == item){
			item = systemDictionaryItemDAO.findAllByDictId(dictId);
			dict.put(dictId, item);
		}
		return item;
	}

	@Override
	@Transactional(readOnly = true)
	public SystemDictionaryItem getSystemDictionaryItemByDictIdAndKey(
			String dictId, String key) {
		for(SystemDictionaryItem i : findSystemDictionaryItemByDictId(dictId)){
			if(i.getDictItemKey().equals(key)){
				return i;
			}
		}
		return null;
	}
	

}
