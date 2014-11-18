package shop.company.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.SystemOperationLogDAO;
import shop.company.entity.SystemOperationLog;

@Service
@Transactional
public class SystemOperationLogServiceImpl implements SystemOperationLogService {

	@Autowired()
	private SystemOperationLogDAO systemOperationLogDAO;

	public SystemOperationLogDAO getSystemOperationLogDAO() {
		return systemOperationLogDAO;
	}

	public void setSystemOperationLogDAO(SystemOperationLogDAO systemOperationLogDAO) {
		this.systemOperationLogDAO = systemOperationLogDAO;
	}

	@Override
	public void deleteSystemOperationLog(SystemOperationLog o) {
		systemOperationLogDAO.remove(o);
	}
	
	@Override
	public void deleteSystemOperationLogByIds(Integer[] ids){
		systemOperationLogDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemOperationLog> findAllSystemOperationLog() {
		return systemOperationLogDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SystemOperationLog> findAllSystemOperationLogWithPage(){
		return systemOperationLogDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SystemOperationLog> findAllSystemOperationLogBySearchWithPage(SystemOperationLog o){
		return systemOperationLogDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public SystemOperationLog getSystemOperationLogById(Serializable id) {
		return systemOperationLogDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllSystemOperationLogByNameWithIdAndName(String name) {
		return systemOperationLogDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveSystemOperationLog(SystemOperationLog o) {
		systemOperationLogDAO.persist(o);
	}

	@Override
	public void updateSystemOperationLog(SystemOperationLog o) {
		systemOperationLogDAO.merge(o);
	}
	
	@Override
	public List<SystemOperationLog> findAllSystemOperationLogByIds(Integer[] cids) {
		return systemOperationLogDAO.findAllByIds(cids);
	}

}
