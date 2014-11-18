package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import shop.company.entity.SystemDictionaryItem;

public interface SystemDictionaryItemService {
	
	public void saveSystemDictionaryItem(SystemDictionaryItem o);
	
	public void updateSystemDictionaryItem(SystemDictionaryItem o);
	
	public void deleteSystemDictionaryItem(SystemDictionaryItem o);
	
	public void deleteSystemDictionaryItemByIds(Integer[] ids);
	
	public SystemDictionaryItem getSystemDictionaryItemById(Serializable id);
	
	public List<SystemDictionaryItem> findAllSystemDictionaryItem();
	
	public List<SystemDictionaryItem> findAllSystemDictionaryItemWithPage();
	
	public List<SystemDictionaryItem> findAllSystemDictionaryItemBySearchWithPage(SystemDictionaryItem o);
	
	public Map findAllSystemDictionaryItemByNameWithIdAndName(String name);
	
	public List<SystemDictionaryItem> findAllSystemDictionaryItemByIds(Integer[] cids);
	
	public List<SystemDictionaryItem> findSystemDictionaryItemByDictId(String dictId);
	
	public SystemDictionaryItem getSystemDictionaryItemByDictIdAndKey(String dictId, String key);
	
}
