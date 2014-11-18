package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import shop.company.entity.AuthPurview;
import shop.company.entity.AuthUser;

public interface AuthPurviewService {
	
	public void saveAuthPurview(AuthPurview o);
	
	public void updateAuthPurview(AuthPurview o);
	
	public void deleteAuthPurview(AuthPurview o);
	
	public void deleteAuthPurviewByIds(String[] ids);
	
	public AuthPurview getAuthPurviewById(Serializable id);
	
	public List<AuthPurview> findAllAuthPurview();
	
	public List<AuthPurview> findAllAuthPurviewWithPage();
	
	public List<AuthPurview> findAllAuthPurviewBySearchWithPage(AuthPurview o);
	
	public Map findAllAuthPurviewByNameWithIdAndName(String name);
	
	public List<AuthPurview> findAllAuthPurviewByIds(String[] cids);

	public List<AuthPurview> findAllAuthPurviewWithKids();

	public List<AuthPurview> findAllAuthPurviewAndKids();

	public List<AuthPurview> findAllAuthPurviewAndKidsByUser(AuthUser user);

}
