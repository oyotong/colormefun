package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import shop.company.entity.SystemFqa;

public interface SystemFqaService {
	
	public void saveSystemFqa(SystemFqa o);
	
	public void updateSystemFqa(SystemFqa o);
	
	public void deleteSystemFqa(SystemFqa o);
	
	public void deleteSystemFqaByIds(Integer[] ids);
	
	public SystemFqa getSystemFqaById(Serializable id);
	
	public List<SystemFqa> findAllSystemFqa();
	
	public List<SystemFqa> findAllSystemFqaWithPage();
	
	public List<SystemFqa> findAllSystemFqaBySearchWithPage(SystemFqa o);
	
	public Map findAllSystemFqaByNameWithIdAndName(String name);
	
	public List<SystemFqa> findAllSystemFqaByIds(Integer[] cids);
	
}
