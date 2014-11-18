package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.SystemDbBackupDAO;
import shop.company.entity.SystemDbBackup;

@Service
@Transactional
public class SystemDbBackupServiceImpl implements SystemDbBackupService {

	@Autowired()
	private SystemDbBackupDAO systemDbBackupDAO;

	public SystemDbBackupDAO getSystemDbBackupDAO() {
		return systemDbBackupDAO;
	}

	public void setSystemDbBackupDAO(SystemDbBackupDAO systemDbBackupDAO) {
		this.systemDbBackupDAO = systemDbBackupDAO;
	}

	@Override
	public void deleteSystemDbBackup(SystemDbBackup o) {
		systemDbBackupDAO.remove(o);
	}
	
	@Override
	public void deleteSystemDbBackupByIds(Integer[] ids){
		systemDbBackupDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemDbBackup> findAllSystemDbBackup() {
		return systemDbBackupDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SystemDbBackup> findAllSystemDbBackupWithPage(){
		return systemDbBackupDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SystemDbBackup> findAllSystemDbBackupBySearchWithPage(SystemDbBackup o){
		return systemDbBackupDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public SystemDbBackup getSystemDbBackupById(Serializable id) {
		return systemDbBackupDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllSystemDbBackupByNameWithIdAndName(String name) {
		return systemDbBackupDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveSystemDbBackup(SystemDbBackup o) {
		systemDbBackupDAO.persist(o);
	}

	@Override
	public void updateSystemDbBackup(SystemDbBackup o) {
		systemDbBackupDAO.merge(o);
	}
	
	@Override
	public List<SystemDbBackup> findAllSystemDbBackupByIds(Integer[] cids) {
		return systemDbBackupDAO.findAllByIds(cids);
	}

}
