package shop.company.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.SystemLoginLogDAO;
import shop.company.entity.SystemLoginLog;

@Service
@Transactional
public class SystemLoginLogServiceImpl implements SystemLoginLogService {

	@Autowired()
	private SystemLoginLogDAO systemLoginLogDAO;

	public SystemLoginLogDAO getSystemLoginLogDAO() {
		return systemLoginLogDAO;
	}

	public void setSystemLoginLogDAO(SystemLoginLogDAO systemLoginLogDAO) {
		this.systemLoginLogDAO = systemLoginLogDAO;
	}

	@Override
	public void deleteSystemLoginLog(SystemLoginLog o) {
		systemLoginLogDAO.remove(o);
	}
	
	@Override
	public void deleteSystemLoginLogByIds(Integer[] ids){
		systemLoginLogDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemLoginLog> findAllSystemLoginLog() {
		return systemLoginLogDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SystemLoginLog> findAllSystemLoginLogWithPage(){
		return systemLoginLogDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SystemLoginLog> findAllSystemLoginLogBySearchWithPage(SystemLoginLog o){
		return systemLoginLogDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public SystemLoginLog getSystemLoginLogById(Serializable id) {
		return systemLoginLogDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllSystemLoginLogByNameWithIdAndName(String name) {
		return systemLoginLogDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveSystemLoginLog(SystemLoginLog o) {
		systemLoginLogDAO.persist(o);
	}

	@Override
	public void updateSystemLoginLog(SystemLoginLog o) {
		systemLoginLogDAO.merge(o);
	}
	
	@Override
	public List<SystemLoginLog> findAllSystemLoginLogByIds(Integer[] cids) {
		return systemLoginLogDAO.findAllByIds(cids);
	}

}
