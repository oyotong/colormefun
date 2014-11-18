package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.SystemFqaDAO;
import shop.company.entity.SystemFqa;

@Service
@Transactional
public class SystemFqaServiceImpl implements SystemFqaService {

	@Autowired()
	private SystemFqaDAO systemFqaDAO;

	public SystemFqaDAO getSystemFqaDAO() {
		return systemFqaDAO;
	}

	public void setSystemFqaDAO(SystemFqaDAO systemFqaDAO) {
		this.systemFqaDAO = systemFqaDAO;
	}

	@Override
	public void deleteSystemFqa(SystemFqa o) {
		systemFqaDAO.remove(o);
	}
	
	@Override
	public void deleteSystemFqaByIds(Integer[] ids){
		systemFqaDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemFqa> findAllSystemFqa() {
		return systemFqaDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SystemFqa> findAllSystemFqaWithPage(){
		return systemFqaDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SystemFqa> findAllSystemFqaBySearchWithPage(SystemFqa o){
		return systemFqaDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public SystemFqa getSystemFqaById(Serializable id) {
		return systemFqaDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllSystemFqaByNameWithIdAndName(String name) {
		return systemFqaDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveSystemFqa(SystemFqa o) {
		systemFqaDAO.persist(o);
	}

	@Override
	public void updateSystemFqa(SystemFqa o) {
		systemFqaDAO.merge(o);
	}
	
	@Override
	public List<SystemFqa> findAllSystemFqaByIds(Integer[] cids) {
		return systemFqaDAO.findAllByIds(cids);
	}

}
