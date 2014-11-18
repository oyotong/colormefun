package shop.company.service;

import java.io.Serializable;
import java.util.*;

import shop.company.entity.SystemLoginLog;

public interface SystemLoginLogService {
	
	public void saveSystemLoginLog(SystemLoginLog o);
	
	public void updateSystemLoginLog(SystemLoginLog o);
	
	public void deleteSystemLoginLog(SystemLoginLog o);
	
	public void deleteSystemLoginLogByIds(Integer[] ids);
	
	public SystemLoginLog getSystemLoginLogById(Serializable id);
	
	public List<SystemLoginLog> findAllSystemLoginLog();
	
	public List<SystemLoginLog> findAllSystemLoginLogWithPage();
	
	public List<SystemLoginLog> findAllSystemLoginLogBySearchWithPage(SystemLoginLog o);
	
	public Map findAllSystemLoginLogByNameWithIdAndName(String name);
	
	public List<SystemLoginLog> findAllSystemLoginLogByIds(Integer[] cids);
	
}
