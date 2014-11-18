package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import shop.company.entity.SystemDbBackup;

public interface SystemDbBackupService {
	
	public void saveSystemDbBackup(SystemDbBackup o);
	
	public void updateSystemDbBackup(SystemDbBackup o);
	
	public void deleteSystemDbBackup(SystemDbBackup o);
	
	public void deleteSystemDbBackupByIds(Integer[] ids);
	
	public SystemDbBackup getSystemDbBackupById(Serializable id);
	
	public List<SystemDbBackup> findAllSystemDbBackup();
	
	public List<SystemDbBackup> findAllSystemDbBackupWithPage();
	
	public List<SystemDbBackup> findAllSystemDbBackupBySearchWithPage(SystemDbBackup o);
	
	public Map findAllSystemDbBackupByNameWithIdAndName(String name);
	
	public List<SystemDbBackup> findAllSystemDbBackupByIds(Integer[] cids);
	
}
