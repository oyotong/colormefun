package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.AuthRoleDAO;
import shop.company.entity.AuthRole;

@Service
@Transactional
public class AuthRoleServiceImpl implements AuthRoleService {

	@Autowired()
	private AuthRoleDAO authRoleDAO;

	public AuthRoleDAO getAuthRoleDAO() {
		return authRoleDAO;
	}

	public void setAuthRoleDAO(AuthRoleDAO authRoleDAO) {
		this.authRoleDAO = authRoleDAO;
	}

	@Override
	public void deleteAuthRole(AuthRole o) {
		authRoleDAO.remove(o);
	}
	
	@Override
	public void deleteAuthRoleByIds(String[] ids){
		authRoleDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AuthRole> findAllAuthRole() {
		return authRoleDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AuthRole> findAllAuthRoleWithPage(){
		return authRoleDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AuthRole> findAllAuthRoleBySearchWithPage(AuthRole o){
		return authRoleDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public AuthRole getAuthRoleById(Serializable id) {
		return authRoleDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllAuthRoleByNameWithIdAndName(String name) {
		return authRoleDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveAuthRole(AuthRole o) {
		authRoleDAO.persist(o);
	}

	@Override
	public void updateAuthRole(AuthRole o) {
		authRoleDAO.merge(o);
	}
	
	@Override
	public List<AuthRole> findAllAuthRoleByIds(String[] cids) {
		return authRoleDAO.findAllByIds(cids);
	}

	@Override
	public List<AuthRole> findAllAuthRoleWithoutPurview() {
		return authRoleDAO.findAllWithoutPurview();
	}

}
