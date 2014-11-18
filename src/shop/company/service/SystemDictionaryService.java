package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import shop.company.entity.SystemDictionary;

public interface SystemDictionaryService {
	
	public void saveSystemDictionary(SystemDictionary o);
	
	public void updateSystemDictionary(SystemDictionary o);
	
	public void deleteSystemDictionary(SystemDictionary o);
	
	public void deleteSystemDictionaryByIds(String[] ids);
	
	public SystemDictionary getSystemDictionaryById(Serializable id);
	
	public List<SystemDictionary> findAllSystemDictionary();
	
	public List<SystemDictionary> findAllSystemDictionaryWithPage();
	
	public List<SystemDictionary> findAllSystemDictionaryBySearchWithPage(SystemDictionary o);
	
	public Map findAllSystemDictionaryByNameWithIdAndName(String name);
	
	public List<SystemDictionary> findAllSystemDictionaryByIds(String[] cids);
	
}
