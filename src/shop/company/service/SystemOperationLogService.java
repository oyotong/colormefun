package shop.company.service;

import java.io.Serializable;
import java.util.*;

import shop.company.entity.SystemOperationLog;

public interface SystemOperationLogService {
	
	public void saveSystemOperationLog(SystemOperationLog o);
	
	public void updateSystemOperationLog(SystemOperationLog o);
	
	public void deleteSystemOperationLog(SystemOperationLog o);
	
	public void deleteSystemOperationLogByIds(Integer[] ids);
	
	public SystemOperationLog getSystemOperationLogById(Serializable id);
	
	public List<SystemOperationLog> findAllSystemOperationLog();
	
	public List<SystemOperationLog> findAllSystemOperationLogWithPage();
	
	public List<SystemOperationLog> findAllSystemOperationLogBySearchWithPage(SystemOperationLog o);
	
	public Map findAllSystemOperationLogByNameWithIdAndName(String name);
	
	public List<SystemOperationLog> findAllSystemOperationLogByIds(Integer[] cids);
	
}
