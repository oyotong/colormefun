package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import shop.company.entity.AuthRole;

public interface AuthRoleService {
	
	public void saveAuthRole(AuthRole o);
	
	public void updateAuthRole(AuthRole o);
	
	public void deleteAuthRole(AuthRole o);
	
	public void deleteAuthRoleByIds(String[] ids);
	
	public AuthRole getAuthRoleById(Serializable id);
	
	public List<AuthRole> findAllAuthRole();
	
	public List<AuthRole> findAllAuthRoleWithPage();
	
	public List<AuthRole> findAllAuthRoleBySearchWithPage(AuthRole o);
	
	public Map findAllAuthRoleByNameWithIdAndName(String name);
	
	public List<AuthRole> findAllAuthRoleByIds(String[] cids);

	public List<AuthRole> findAllAuthRoleWithoutPurview();
	
}
