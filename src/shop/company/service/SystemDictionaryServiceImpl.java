package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.SystemDictionaryDAO;
import shop.company.entity.SystemDictionary;

@Service
@Transactional
public class SystemDictionaryServiceImpl implements SystemDictionaryService {

	@Autowired()
	private SystemDictionaryDAO systemDictionaryDAO;

	public SystemDictionaryDAO getSystemDictionaryDAO() {
		return systemDictionaryDAO;
	}

	public void setSystemDictionaryDAO(SystemDictionaryDAO systemDictionaryDAO) {
		this.systemDictionaryDAO = systemDictionaryDAO;
	}

	@Override
	public void deleteSystemDictionary(SystemDictionary o) {
		systemDictionaryDAO.remove(o);
	}
	
	@Override
	public void deleteSystemDictionaryByIds(String[] ids){
		systemDictionaryDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemDictionary> findAllSystemDictionary() {
		return systemDictionaryDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SystemDictionary> findAllSystemDictionaryWithPage(){
		return systemDictionaryDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SystemDictionary> findAllSystemDictionaryBySearchWithPage(SystemDictionary o){
		return systemDictionaryDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public SystemDictionary getSystemDictionaryById(Serializable id) {
		return systemDictionaryDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllSystemDictionaryByNameWithIdAndName(String name) {
		return systemDictionaryDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveSystemDictionary(SystemDictionary o) {
		systemDictionaryDAO.persist(o);
	}

	@Override
	public void updateSystemDictionary(SystemDictionary o) {
		systemDictionaryDAO.merge(o);
	}
	
	@Override
	public List<SystemDictionary> findAllSystemDictionaryByIds(String[] cids) {
		return systemDictionaryDAO.findAllByIds(cids);
	}

}
